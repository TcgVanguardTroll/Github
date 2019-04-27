package View

import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.control.TextInputDialog
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.{Group, Scene}

import scala.collection.mutable.ArrayBuffer

object Agario extends JFXApp {
  // 1000
  val windowWidth: Double = 1000
  // 600
  val windowHeight: Double = 600
  val Width: Double = 80
  val Height: Double = 60
  //players initial radius from the start of the game
  var playerCircleRadius: Double = 10
  //players speed
  var playerSpeed: Double = 15
  // Mutable Array full of all the blobs
  var allCircles: ArrayBuffer[Circle] = ArrayBuffer()
  // What appears to the screen
  var sceneGraphics: Group = new Group {}
  //  This racing will reference the players position(translate.x or translate.y)
  var racing: Double = 0.0
  //This is the creation of the player/user
  val player: Circle = new Circle {
    //    setting a radius equal to the player radius variable from above
    radius = playerCircleRadius
    fill = Color.Green
  }
  //Players starting point/ and then making it appear on the GUI.
  player.centerX.value = 10
  player.centerY.value = 10
  sceneGraphics.children.add(player)
  //Creating another player
//  val players: Circle = new Circle{
//      centerX = Math.random() * Width
//      centerY = Math.random() * Height
//      radius = playerCircleRadius
//      fill = Color.Yellow
//    }
//    sceneGraphics.children.add(players)
  // The creation of all the little blobs.
  def drawBlob(): Unit = {
    playerCircleRadius = 9
    for (multiple <- 0 to 20) {
      var circles = new Circle() {
        centerX = Math.random() * (windowWidth - playerCircleRadius)
        centerY = Math.random() * (windowHeight - playerCircleRadius)
        radius = playerCircleRadius
        fill = Color.CornflowerBlue
      }
      sceneGraphics.children.add(circles)
      allCircles += circles
    }
  }
  def drawBlobs(): Unit = {
    playerCircleRadius = 9
    for (multiple <- 0 to 1) {
      var circles = new Circle() {
        centerX = Math.random() * (windowWidth - playerCircleRadius)
        centerY = Math.random() * (windowHeight - playerCircleRadius)
        radius = playerCircleRadius
        fill = Color.CornflowerBlue
      }
      sceneGraphics.children.add(circles)
      allCircles += circles
    }
  }
    // This is where the blobs delete
    def delete(): Unit = {
      for (blobs <- allCircles) {
        // Calculating the distance of the radius of the blob and the player
        val distance = Math.sqrt(Math.pow((blobs.centerX.value - player.translateX.value), 2) + Math.pow((blobs.centerY.value - player.translateY.value), 2))
        // Setting a variable that combines the players variable and the radius
        val radius2 = player.radius.value + blobs.radius.value
        // this checks if the radius is greater that the distance, if it is then the players radius will increase and
        // will then be displayed onto the screen.
        if (radius2 > distance) {
          drawBlob()
          player.radius.value += 1
          sceneGraphics.children.remove(blobs)
          blobs.centerX.value = -1000
        }
        // Once the players radius starts getting bigger, the speed will decrease just like in the actual game.
        if (player.radius.value > 50) {
          playerSpeed = 7
        }
        // Blobs wont get too big.
        if (player.radius.value > 100) {
          player.radius.value = 100
        }
      }
    }
    //The user action for the player, on the keyboard.
//    def keyPress(keyCode: KeyCode): Unit = {
//      keyCode.getName match {
//        case "Left" => racing = 1
//        case "Right" => racing = 2
//        case "Up" => racing = 3
//        case "Down" => racing = 4
//        case _ => println(keyCode.getName + " pressed with no action")
//      }
//    }
    // The user action on the key board.
    def keyPressed(keyCode: KeyCode): Unit = {
      keyCode.getName match {
        case "W" => racing = 1
        case "A" => racing = 2
        case "S" => racing = 3
        case "D" => racing = 4
//        case _ => println(keyCode.getName + " pressed with no action")
      }
    }
    //In this method, the bounds are set in place, and the player is able to move continuously
    //When one of the buttons is pressed on the Key Board.
    def bounds(): Unit = {
      //These are just variables that will represent the players blob speed
      var yspeed: Int = 0
      var xspeed: Int = 0
      // This is where the players speed is set to continue when the user presses one of the buttons.
      if (racing == 1) {
        yspeed = -2
        xspeed = 0
      }else if (racing == 2) {
        yspeed = 0
        xspeed = -2
      }else if (racing == 3) {
        yspeed = 2
        xspeed = 0
      }else if (racing == 4) {
        yspeed = 0
        xspeed = 2
      }
      // These statements are were the bounds are set.
      if (player.translateY.value > windowHeight - player.radius.value) {
        player.translateY.value = windowHeight - player.radius.value
      }else if (0 > player.translateY.value) {
        player.translateY.value = 0
      }else if (player.translateX.value > windowWidth - player.radius.value) {
        player.translateX.value = windowWidth - player.radius.value
      }else if (player.translateX.value < 0) {
        player.translateX.value = 0
      }
      player.translateY.value += yspeed
      player.translateX.value += xspeed
    }
    //This is where it will then be displayed.
    this.stage = new PrimaryStage {
//The Login Screen
      this.title = "Agar.IO"
      scene = new Scene(Height, Width) {
        val dialog = new TextInputDialog(defaultValue = " ") {
          initOwner(stage)
          title = "Welcome to Agar.IO"
          headerText = "Enter Your UserName"
          contentText = "Enter Username:"
        }
        val result = dialog.showAndWait()
        result match {
          case Some(name) => println("Your name: " + name)
          case None => println("Please Enter UserName!")
        }
      }
      scene = new Scene(windowWidth, windowHeight) {
        content = List(sceneGraphics)
        addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
//        addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPress(event.getCode))
        drawBlobs()
      }
      // This will continuously loop over the methods, therefore the food can continue to be eaten.
//Also the bounds will always be set, and the player can move continuously.
      val update: Long => Unit = (time: Long) => {
        delete()
        bounds()
      }
      AnimationTimer(update).start()
    }
  }