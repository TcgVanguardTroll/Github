package Model.TcpServer

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorSystem}
import akka.io.Tcp.{Bound, Connect}
import akka.io.{IO, Tcp}

class TCPServer(ipAddress: InetSocketAddress, actorSystem: ActorSystem) extends Actor {
  IO(Tcp)(actorSystem) ! Connect(ipAddress)
  var buffer: String = ""
  val delimiter: String = "~"

  override def receive: Receive = {
    case b: Bound => println("Listening on port: " + b.localAddress.getPort)

  }

  object TCPServer extends App {


  }
