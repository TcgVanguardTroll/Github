package towers.model.game_objects

import play.api.libs.json.{JsValue, Json}
import towers.model.genetics.genes.Gene
import towers.model.physics.PhysicsVector

class DodgeBallTower(val x: Int, val y: Int) extends GameObject {

  // The height at which projectiles are fired
  val height = 3.0

  // Towers can only fire at players closer than this distance from the tower
  val sightRange = 5.0

  // The magnitude of the velocity at which projectiles are fired
  val projectileVelocity = 5.0

  def sort_x(players_details : Map[String,JsValue], players_details2 : Map[String,JsValue] ): Boolean ={
    var x_rt = (players_details("x")).as[Double]
    var y_rt = (players_details("y")).as[Double]
    var x_rt2 = (players_details2("x")).as[Double]
    var y_rt2 = (players_details2("y")).as[Double]
    new PhysicsVector(this.x + .5,this.y + .5).distance2d(new PhysicsVector(x_rt,y_rt)) < new PhysicsVector(this.x + .5,this.y + .5).distance2d(new PhysicsVector(x_rt2,y_rt2))
  }


  def fire(jsonGameState: String): List[Projectile] = {
    // TODO: Objective 2
    var list_projectiles : List[Projectile] = List()
    var game_details = Json.parse(jsonGameState)
    var players = (game_details \ "players").as[List[Map[String, JsValue]]]
    players = players.sortWith(sort_x)
      var x_val = players.head("x").as[Double]
      var y_val = players.head("y").as[Double]
      var distance = new PhysicsVector(x + .5, y + .5).distance2d(new PhysicsVector(x_val, y_val))
      if (distance < sightRange) {
        var projectileLoc = new PhysicsVector(x + .5, y + .5, height)
        var projectileVel = new PhysicsVector(x_val - x - .5, y_val - y - .5).normal2d()
        var normal = new PhysicsVector(projectileVel.x * projectileVelocity, projectileVel.y * projectileVelocity, 0)
        list_projectiles = list_projectiles :+ new Projectile(projectileLoc, normal)
    }
    list_projectiles
  }


  def aimFire(jsonGameState: String): List[Projectile] = {
    // TODO: Bonus Objective
    List()
  }


  // Suggested Genetic Algorithm setup
  def getFitnessFunction(targetPlayer: Player): PhysicsVector => Double = {
    null
  }

  def vectorIncubator(genes: List[Gene]): PhysicsVector = {
    null
  }

}
