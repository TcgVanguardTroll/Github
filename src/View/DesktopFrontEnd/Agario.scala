package View.DesktopFrontEnd

import javafx.scene.input.{KeyCode, KeyEvent}
import javax.swing.JTextField
import play.api.libs.json.{JsValue, Json}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.DoubleProperty
import scalafx.beans.value.ObservableValue
import scalafx.scene.control.{TableColumn, TextField, TextInputDialog}
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle
import scalafx.scene.{Group, Scene}
import agar.Games

import scala.collection.mutable.ListBuffer


  object Agario extends JFXApp {
    

    val windowWidth: Double = 1300
    val windowHeight: Double = 750
    val Width: Double = 80
    val Height: Double = 60
    var playerCircleRadius: Double = 12
    var playerSpeed: Double = 10
    var allCircles: ListBuffer[Circle] = ListBuffer()
    var sceneGraphics: Group = new Group {}
    var circleList: ListBuffer[Circle] = ListBuffer()
    var playerList: ListBuffer[Circle] = ListBuffer()
    var racing: Double = 0.0
    var _speed: Double = -2
    var speed: Double = 2
    var xspeed: Double = 0
    var yspeed: Double = 0

    var lastUpdateTime: Long = System.nanoTime()

    val player: Circle = new Circle {
      centerX = Math.random() * windowWidth
      centerY = Math.random() * windowHeight
      radius = playerCircleRadius
      fill = Color.Green
      stroke = Color.Black
      strokeWidth = 3
      //    var player1 = new Positions(centerX.get(),centerY.get(),radius.get())
      //    var player1_player_class = new Players(player1,true,"ahamed")
    }

    val player2: Circle = new Circle {
      centerX = Math.random() * windowWidth
      centerY = Math.random() * windowHeight
      radius = 20
      fill = Color.Black
      playerList += player2
      stroke = Color.Lavender
      strokeWidth = 2
    }

    sceneGraphics.children.add(player)
    sceneGraphics.children.add(player2)

    val players: Circle = new Circle {
      centerX = Math.random() * windowWidth
      centerY = Math.random() * windowHeight
      radius = playerCircleRadius
      fill = Color.Green
      playerList += players
    }
    val spikes: Circle = new Circle {
      centerX = Math.random() * windowWidth
      centerY = Math.random() * windowHeight
      radius = 20
      fill = Color.Red
      stroke = Color.Black
      strokeWidth = 3
    }
    sceneGraphics.children.add(spikes)
    var playing = new Group(players)
    sceneGraphics.children.add(playing)

    //  var all_food_positions: ListBuffer[Positions] = ListBuffer()

    def drawCircles(): Unit = {
      playerCircleRadius = 15
      for (multiple <- 1 to 1) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          radius = playerCircleRadius
          fill = Color.DarkRed
          stroke = Color.Red
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
      }
    }

    def drawCircles_speed(): Unit = {
      playerCircleRadius = 10
      for (multiple <- 1 to 1) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          radius = playerCircleRadius
          fill = Color.Green
          stroke = Color.Aqua
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
      }
    }

    def mass_25_circle(): Unit = {
      playerCircleRadius = 8
      for (multiple <- 0 to 0) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          radius = playerCircleRadius
          fill = Color.BlueViolet
          stroke = Color.Lavender
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
        //      var new_circles = new Positions(x.get(), y.get(), playerCircleRadius)
        //      all_food_positions += new_circles
      }
    }

    def draw_normal_circle(): Unit = {
      playerCircleRadius = 11
      for (multiple <- 0 to 0) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          radius = playerCircleRadius
          fill = Color.Yellow
          stroke = Color.RosyBrown
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
        //      var new_circles = new Positions(x.get(), y.get(), playerCircleRadius)
        //      all_food_positions += new_circles
      }
    }

    def draw_FAST_circle(): Unit = {
      playerCircleRadius = 10.5
      for (multiple <- 0 to 0) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          radius = playerCircleRadius
          fill = Color.DarkOrange
          stroke = Color.Yellow
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
        //    println(all_food_positions)
      }
    }

    def drawCircle(): Unit = {
      playerCircleRadius = 10
      var x: DoubleProperty = null
      var y: DoubleProperty = null
      for (multiple <- 0 to 6) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          x = centerX
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          y = centerY
          radius = playerCircleRadius
          fill = Color.Green
          stroke = Color.Aqua
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
        //      var new_circles = new Positions(x.get(), y.get(), playerCircleRadius)
        //      all_food_positions += new_circles
      }
      playerCircleRadius = 11
      for (multiple <- 0 to 20) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          x = centerX
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          y = centerY
          radius = playerCircleRadius
          fill = Color.BlueViolet
          stroke = Color.RosyBrown
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
        //      var new_circles = new Positions(x.get(), y.get(), playerCircleRadius)
        //      all_food_positions += new_circles
      }
      playerCircleRadius = 8
      for (multiple <- 0 to 70) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          x = centerX
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          y = centerY
          radius = playerCircleRadius
          fill = Color.Gold
          stroke = Color.Lavender
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
        //      var new_circles = new Positions(x.get(), y.get(), playerCircleRadius)
        //      all_food_positions += new_circles
      }
      playerCircleRadius = 10.5
      for (multiple <- 0 to 9) {
        var circles = new Circle() {
          centerX = Math.random() * (windowWidth - playerCircleRadius)
          x = centerX
          centerY = Math.random() * (windowHeight - playerCircleRadius)
          y = centerY
          radius = playerCircleRadius
          fill = Color.DarkOrange
          stroke = Color.Yellow
          strokeWidth = 1
        }
        sceneGraphics.children.add(circles)
        allCircles += circles
        //    println(all_food_positions)
      }
    }

    def keyPress(keyCode: KeyCode): Unit = {
      keyCode.getName match {
        case "Left" => player.translateY.value -= playerSpeed
        case "Right" => player.translateX.value -= playerSpeed
        case "Up" => player.translateY.value += playerSpeed
        case "Down" => player.translateX.value += playerSpeed
        case _ => println(keyCode.getName + " pressed with no action")
      }
    }

    def keyPressed(keyCode: KeyCode): Unit = {
      keyCode.getName match {
        case "W" => racing = 1
        case "A" => racing = 2
        case "S" => racing = 3
        case "D" => racing = 4
        case _ => println(keyCode.getName + " pressed with no action")
      }
    }


    def bounds(): Unit = {
      if (racing == 1) {
        yspeed = _speed
        xspeed = 0
      }
      else if (racing == 2) {
        yspeed = 0
        xspeed = _speed
      }
      else if (racing == 3) {
        yspeed = speed
        xspeed = 0
      }
      else if (racing == 4) {
        yspeed = 0
        xspeed = speed
      }


      if (player.centerY.value > windowHeight - player.radius.value) {
        player.centerY.value = windowHeight - player.radius.value
      }
      else if (0 > player.centerY.value) {
        player.centerY.value = 0
      }
      else if (player.centerX.value > windowWidth - player.radius.value) {
        player.centerX.value = windowWidth - player.radius.value
      }
      else if (player.centerX.value < 0) {
        player.centerX.value = 0
      }
      player.centerY.value += yspeed
      player.centerX.value += xspeed
    }

    this.stage = new PrimaryStage {
      this.title = "LeaderBoard"
      scene = new Scene(Height, Width) {
      }

    }
    this.stage = new PrimaryStage {
      this.title = "Agar.IO"
      scene = new Scene(Height, Width) {

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
          case None => println("Username Taken, Try Again")
        }
      }


      fullScreen = true
      resizable = true
      scene = new Scene(windowWidth, windowHeight) {
        val textBox = new TextField {
          /* ... */
        }
        val boxText: ObservableValue[String, String] = textBox.text
        content = List(sceneGraphics)
        addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
        //addEventHandler(MouseEvent.MOUSE_CLICKED, (event: MouseEvent) => drawCircle())
        drawCircle()

        val update: Long => Unit = (time: Long) => {
          bounds()
          val dt: Double = (time - lastUpdateTime) / 1000000000.0
          lastUpdateTime = time
          //          Games.
          //          if(time == 3.0){
          //            for (multiple <- 0 to 1) {
          //              var circles = new Circle() {
          //                centerX = Math.random() * (windowWidth - playerCircleRadius)
          //                centerY = Math.random() * (windowHeight - playerCircleRadius)
          //                radius = playerCircleRadius
          //                fill = Color.CornflowerBlue
          //              }
          //              sceneGraphics.children.add(circles)
          //              allCircles += circles
          //              //      var new_circles = new Positions(x.get(), y.get(), playerCircleRadius)
          //              //      all_food_positions += new_circles
          //            }
          //          }
          for (circle <- allCircles) {
            if (Games.hit_Foods(player, circle) == true) {
              if (circle.radius.value == 8) {
                //              if(circle.fill == Color.Yellow){}
                allCircles -= circle
                player.radius = player.radius.value + .05
                circle.disable = true
                circle.visible = false
                draw_normal_circle()
              }
            }
            if (Games.hit_Foods(player, circle) == true) {
              if (circle.radius.value == 11) {
                //              if(circle.fill == Color.Yellow){}
                allCircles -= circle
                player.radius = player.radius.value + 0.25
                circle.disable = true
                circle.visible = false
                drawCircles()
              }
            }
            if (Games.hit_Foods(player, circle) == true) {
              if (circle.radius.value == 10) {
                //              if(circle.fill == Color.Yellow){}
                allCircles -= circle
                player.centerY.value += yspeed + (-200 + Math.random * (200 - -200))
                player.centerX.value += xspeed + (-200 + Math.random * (200 - -200))
                player.radius = player.radius.value - 0.2
                circle.disable = true
                circle.visible = false
                mass_25_circle()
              }
            }
            if (Games.hit_Foods(player, circle) == true) {
              if (circle.radius.value == 15) {
                //              if(circle.fill == Color.Yellow){}
                allCircles -= circle
                playerSpeed = playerSpeed - 2
                player.radius = player.radius.value + 0.4
                _speed = _speed + 0.05
                speed = speed + -0.05
                circle.disable = true
                circle.visible = false
                draw_FAST_circle()
              }
            }
            if (Games.hit_Foods(player, circle) == true) {
              if (circle.radius.value == 10.5) {
                //              if(circle.fill == Color.Yellow){}
                allCircles -= circle
                player.radius = player.radius.value - 0.2
                _speed = _speed + -0.2
                speed = speed + 0.2
                circle.disable = true
                circle.visible = false
                drawCircles_speed()
              }
            }
          }

          if (Games.hit_Foods(player, player2) == true) {

            player.radius = player.radius.value + 0.4
            player2.disable = true
            player2.visible = false
          }
          if (Games.hit_Foods(player, spikes) == true) {
            player.radius = player.radius.value / 2
          }
        }
        AnimationTimer(update).start()
      }
    }
  }
