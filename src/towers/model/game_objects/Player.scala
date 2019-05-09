package towers.model.game_objects

import towers.model.physics.PhysicsVector

class Player(inputLocation: PhysicsVector,
             inputVelocity: PhysicsVector,
             value:Double) extends PhysicalObject(inputLocation, inputVelocity,3) {

  val speed: Double = 4.0

  def move(direction: PhysicsVector){
    val normalDirection = direction.normal2d()
    this.velocity = new PhysicsVector(normalDirection.x * speed, normalDirection.y * speed)
  }

  def stop(): Unit ={
    this.velocity = new PhysicsVector(0, 0)
  }

}
