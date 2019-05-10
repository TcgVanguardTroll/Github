package Model_old.TcpServer

import java.net.InetSocketAddress

import Model.TcpServer.GameActor
import akka.actor.{Actor, ActorSystem, Props}
import akka.io.Tcp._
import akka.io.{IO, Tcp}
import akka.util.ByteString
import play.api.libs.json.{JsValue, Json}
import towers.model.{MovePlayer, StopPlayer}

class TCPConnectionSupervisor(ipAddress: InetSocketAddress, actorSystem: ActorSystem) extends Actor {
  IO(Tcp)(actorSystem) ! Connect(ipAddress)
      //dajfajbjbj
  override def receive: Receive = {
    case b: Bound => println("Server Started On " + b.localAddress.getPort)

    case c: Connected =>
      val supervisor = context.actorOf(Props[TCPConnectionSupervisor])
      println("Client Connected: " + c.remoteAddress)
      sender() ! Register(supervisor)
  }
}

class TCPConnection extends Actor {
  override def receive: Actor.Receive = {
    case Received(data) =>
      val decoded = data.utf8String
      sender() ! Write(ByteString(s"You told us: $decoded"))
    case closed: ConnectionClosed =>
      println("Connection has been closed")
      context stop self
  }

  def handleMessageFromWebServer(messageString: String): Unit = {
    val message: JsValue = Json.parse(messageString)
    val username = (message \ "username").as[String]
    val messageType = (message \ "action").as[String]

    messageType match {
      case "connected" =>
      case "disconnected" =>
      case "move" =>
        val x = (message \ "x").as[Double]
        val y = (message \ "y").as[Double]
        gameActor ! MovePlayer(username, x, y)
      case "stop" => gameActor ! StopPlayer(username)
    }
  }
}
object TCPServer extends App {
  val system = ActorSystem()
  val tcpserver = system.actorOf(Props(classOf[TCPConnectionSupervisor], new InetSocketAddress("localhost",
    8080), system))
}

