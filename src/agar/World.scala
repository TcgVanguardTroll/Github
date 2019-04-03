package agar

import scala.collection.mutable.ListBuffer

class World(var boundaries: List[Boundary] = List()) {
  var Players: List[Players] = List()
  var Foods: ListBuffer[Positions] = ListBuffer()


}
