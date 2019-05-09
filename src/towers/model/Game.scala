package towers.model

import play.api.libs.json.{JsValue, Json}
import towers.model.game_objects._
import towers.model.physics.{Physics, PhysicsVector, World}

import scala.collection.mutable.ListBuffer


class Game {
//dalkd
  val world: World = new World(10)

  var towers: List[DodgeBallTower] = List()
  var walls: List[Wall] = List()
  var projectiles: List[PhysicalObject] = List()
  var Foods: ListBuffer[Food] = ListBuffer()
  var remove_players: List[String] = List()

  var baseHealth = 10

  var level: Level = new Level()

  var players: Map[String, Player] = Map()
  val playerSize: Double = 0.3

  var lastUpdateTime: Long = System.nanoTime()


  def loadLevel(newLevel: Level): Unit = {
    world.boundaries = List()
    level = newLevel

    projectiles.foreach(po => po.destroy())
    towers = List()
    walls = List()
    projectiles = List()

    blockTile(0, 0, level.gridWidth, level.gridHeight)

    level.towerLocations.foreach(tower => placeTower(tower.x, tower.y))
    level.startingLocation.foreach(food => placeFood(food.x,food.y))
    players.values.foreach(player => player.location = startingVector())

    baseHealth = level.maxBaseHealth
  }

  def placeFood(x: Int,y: Int): Unit ={
    Foods = Foods :+ new Food(x,y)
  }

  def addPlayer(id: String): Unit = {
    val player = new Player(startingVector(), new PhysicsVector(0, 0),12)
    players += (id -> player)
    world.objects = player :: world.objects
  }


  def removePlayer(id: String): Unit = {
    players(id).destroy()
    players -= id
  }

  def blockTile(x: Int, y: Int, width: Int = 1, height: Int = 1): Unit = {
    val ul = new PhysicsVector(x, y)
    val ur = new PhysicsVector(x + width, y)
    val lr = new PhysicsVector(x + width, y + height)
    val ll = new PhysicsVector(x, y + height)

    world.boundaries ::= new Boundary(ul, ur)
    world.boundaries ::= new Boundary(ur, lr)
    world.boundaries ::= new Boundary(lr, ll)
    world.boundaries ::= new Boundary(ll, ul)
  }


  def placeTower(x: Int, y: Int): Unit = {
    towers = new DodgeBallTower(x, y) :: towers
  }


  def addProjectile(projectile: PhysicalObject): Unit = {
    projectiles = projectile :: projectiles
    world.objects = projectile :: world.objects
  }


  def startingVector(): PhysicsVector = {
    val start = 1
    val end   = 40
    val startY = 1
    val endY   = 19
    val rnd = new scala.util.Random
    new PhysicsVector( start + rnd.nextInt( (end - start) + 1 ), startY + rnd.nextInt( (endY - startY) + 1 ))
  }



  def update(): Unit = {
    val time: Long = System.nanoTime()
    val dt = (time - this.lastUpdateTime) / 1000000000.0
    Physics.updateWorld(this.world, dt)
//    checkForPlayerHits()
//    checkForBaseDamage()
    checkForPlayerDamage()
    checkForEatenFood()
    projectiles = projectiles.filter(po => !po.destroyed)
    this.lastUpdateTime = time
  }

  def gameState(): String = {
    val gameState: Map[String, JsValue] = Map(
      "gridSize" -> Json.toJson(Map("x" -> level.gridWidth, "y" -> level.gridHeight)),
//      "start" -> Json.toJson(Map("x" -> level.startingLocation.x, "y" -> level.startingLocation.y)),
      "base" -> Json.toJson(Map("x" -> level.base.x, "y" -> level.base.y)),
      "baseHealth" -> Json.toJson(baseHealth),
      "maxBaseHealth" -> Json.toJson(level.maxBaseHealth),
      "foods" -> Json.toJson(this.Foods.map({ food => Json.toJson(Map("x" -> food.x, "y" -> food.y)) })),
      "walls" -> Json.toJson(this.walls.map({ w => Json.toJson(Map("x" -> w.x, "y" -> w.y)) })),
      "towers" -> Json.toJson(this.towers.map({ t => Json.toJson(Map("x" -> t.x, "y" -> t.y)) })),
      "players" -> Json.toJson(this.players.map({ case (k, v) => Json.toJson(Map(
        "x" -> Json.toJson(v.location.x),
        "y" -> Json.toJson(v.location.y),
        "v_x" -> Json.toJson(v.velocity.x),
        "v_y" -> Json.toJson(v.velocity.y),
        "id" -> Json.toJson(k),
        "radius" -> Json.toJson(v.value))) })),
      "projectiles" -> Json.toJson(this.projectiles.map({ po => Json.toJson(Map("x" -> po.location.x, "y" -> po.location.y)) }))
    )

    Json.stringify(Json.toJson(gameState))
  }


  def checkForPlayerDamage(): Unit = {
    // TODO: Objective 1
    for(player1 <- players.values){
      for(player2 <- players.values){
        var distance = player1.location.distance2d(player2.location)
        if(distance < playerSize){
          if(player1.value > player2.value){
            player1.value += player2.value
            for(player <- players.keys) {
              if (players(player) == player2) {
//                remove_players += player
                removePlayer(player)
              }
            }
          }
        }
      }
    }
  }
  def checkForEatenFood(): Unit = {
    // TODO: Objective 1
    for(player <- players.values){
      for(food <- Foods){
        var distance = player.location.distance2d(new PhysicsVector(food.x,food.y))
        if(distance < playerSize){
          player.value += 1
          Foods -= food
        }
      }
    }
  }
  def checkForBaseDamage(): Unit = {
    // TODO: Objective 1
  }


  def checkForPlayerHits(): Unit = {
    // TODO: Objective 3
  }


}


