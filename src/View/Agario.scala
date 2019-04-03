package View

import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.value.ObservableValue
import scalafx.scene.control.{TableColumn, TextField, TextInputDialog}
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Circle, Shape}
import scalafx.scene.{Group, Scene}

object Agario extends JFXApp {
  val windowWidth: Double = 800
  val windowHeight: Double = 600
  val Width: Double = 80
  val Height: Double = 60
  var playerCircleRadius:Double = 40
  val playerSpeed: Double = 20
  var allCircles: List[Shape] = List()
  var sceneGraphics: Group = new Group {}
  var circleList: List[Shape] = List()

  val player: Circle = new Circle {
    centerX = Math.random() * windowWidth
    centerY = Math.random() * windowHeight
    radius = playerCircleRadius
    fill = Color.Green
  }
  sceneGraphics.children.add(player)
  new TableColumn[Person, String] {
    text = "First Name"
    // Cell value is loaded from a `Person` object
    cellValueFactory = { _.value.firstName }
  }

val players: Circle = new Circle{
    centerX = Math.random() * windowWidth
    centerY = Math.random() * windowHeight
    radius = playerCircleRadius
    fill = Color.Green
  }
  var playing  = new Group(players)
  sceneGraphics.children.add(playing)

  def drawCircle(): Unit = {
    playerCircleRadius = 10
    for (multiple <- 0 to 100) {
      var circles = new Circle() {
        centerX = Math.random() * (windowWidth - playerCircleRadius)
        centerY = Math.random() * (windowHeight - playerCircleRadius)
        radius = playerCircleRadius
        fill = Color.CornflowerBlue
      }
      sceneGraphics.children.add(circles)
      allCircles = circles :: allCircles
    }
  }
  def keyPressed(keyCode: KeyCode): Unit = {
    keyCode.getName match {
      case "W" => player.translateY.value -= playerSpeed
      case "A" => player.translateX.value -= playerSpeed
      case "S" => player.translateY.value += playerSpeed
      case "D" => player.translateX.value += playerSpeed
      case _ => println(keyCode.getName + " pressed with no action")
    }
  }
  this.stage = new PrimaryStage{
    this.title = "LeaderBoard"
    scene = new Scene(Height, Width){
    }

  }
  this.stage = new PrimaryStage {
    this.title = "Agar.IO"
    scene = new Scene(Height, Width){

      val tfInput = new JTextField
      tfInput.setEditable(true)
      //  tfInput.addActionListener();

      val tfOutput = new JTextField
      tfOutput.setEditable(false)

      val dialog = new TextInputDialog(defaultValue = " ") {
        initOwner(stage)
        title = "Welcome to Agario"
        headerText = "Enter Your UserName"
        contentText = "Enter Username:"
      }

      val result = dialog.showAndWait()

      result match {
        case Some(name) => println("Your name: " + name)
        case None       => println("Username Taken, Try Again")
      }
    }
    scene = new Scene(windowWidth, windowHeight) {
      val textBox = new TextField { /* ... */ }
      val boxText: ObservableValue[String, String] = textBox.text
      content = List(sceneGraphics)
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
      //addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => drawCircle())
      drawCircle()
    }
    val update: Long => Unit = (time: Long) => {
      for (shape <- allCircles) {
        shape.rotate.value += 0.5
      }
    }
    AnimationTimer(update).start()
  }

}