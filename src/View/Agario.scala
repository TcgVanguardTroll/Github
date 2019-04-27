package View

import javafx.scene.input.{KeyCode, KeyEvent}
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.value.ObservableValue
import scalafx.scene.control.{TextField, TextInputDialog}
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
  var playerCircleRadius:Double = 10
//players speed
  var playerSpeed: Double = 15
// Mutable Array full of all the blobs
  var allCircles: ArrayBuffer[Circle] = ArrayBuffer()
// What appears to the screen
  var sceneGraphics: Group = new Group {}
//  This racing will reference the players position(translate.x or translate.y)
  var racing: Double = 0.0
//This is the creation of the player/user
  val player3: Circle = new Circle {
//    setting a radius equal to the player radius variable from above
    radius = playerCircleRadius
    fill = Color.Green
  }
//Players starting point/ and then making it appear on the GUI.
  player.centerX.value = 10
  player.centerY.value = 10
  sceneGraphics.children.add(player)
//Creating another player
//val players: Circle = new Circle{
//    centerX = Math.random() * windowWidth
//    centerY = Math.random() * windowHeight
//    radius = playerCircleRadius
//    fill = Color.Green
//  }
//  sceneGraphics.children.add(players)
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
  def drawCircles(): Unit = {
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
  def delete(): Unit = {
    for(blobs <- allCircles){
      val distance = Math.sqrt(Math.pow((blobs.centerX.value - player.translateX.value), 2) + Math.pow((blobs.centerY.value - player.translateY.value),2))
      val radius2 = player.radius.value + blobs.radius.value
      //println(distance + "  " + radius2)

      if(radius2 > distance){
        drawBlob()
        player.radius.value += 1
        sceneGraphics.children.remove(blobs)
        blobs.centerX.value = -1000
      }
      if(player.radius.value > 50){
        playerSpeed = 7
      }
      if(player.radius.value > 75){
        player.radius.value = 75
      }
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


  def bounds():Unit = {
    var yspeed: Int = 0
    var xspeed: Int = 0
    if(racing == 1){
      yspeed = -2
      xspeed = 0
    }
    else if(racing == 2 ){
      yspeed =0
      xspeed = -2
    }
    else if(racing == 3 ){
      yspeed = 2
      xspeed = 0
    }
    else if(racing == 4 ){
      yspeed =0
      xspeed = 2
    }


    if(player.translateY.value > windowHeight - player.radius.value) {
      player.translateY.value = windowHeight - player.radius.value
    }
    else if(0 > player.translateY.value){
      player.translateY.value = 0
    }
    else if(player.translateX.value > windowWidth - player.radius.value) {
      player.translateX.value = windowWidth - player.radius.value
    }
    else if(player.translateX.value <0) {
      player.translateX.value = 0
    }
    player.translateY.value += yspeed
    player.translateX.value += xspeed
  }
  this.stage = new PrimaryStage {
    this.title = "Agar.IO"
    scene = new Scene(Height, Width){
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
//      delete(Height,Width)+
      addEventHandler(KeyEvent.KEY_PRESSED, (event: KeyEvent) => keyPressed(event.getCode))
      drawBlob()

    }
    val update: Long => Unit = (time: Long) => {
      delete()
      bounds()
    }
    AnimationTimer(update).start()

  }

}