package lab

import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.HBox
import scalafx.scene.text.Text


object Activity2 extends JFXApp {

  this.stage = new PrimaryStage {
    title = "Lab Activity 2: Individual"
    scene = new Scene {
      content = new HBox {
        padding = Insets(10)
        children = Seq(
          new Text {
            text = "I completed Lab Activity 2 - Part 1"
            style = "-fx-font: 46 ariel;"
          }
        )
      }
    }
  }
}
