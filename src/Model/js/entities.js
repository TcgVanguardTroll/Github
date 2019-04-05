// Creating A Class For Food

class Food extends Phaser.GameObjects.Sprite(){
    constructor(x,y,depth){
        this.setDepth = depth
        this.sprite = this.game.add.sprite(x,y)     
    }

}



// Craetiing a Class for the Player

class Player extends Phaser.GameObjects.Sprite(){
    constructor(appearence,depth,speed){
        this.setDepth = depth
        this.size = size
        this.speed = speed
    }
    
}