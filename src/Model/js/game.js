var config = {
    type: Phaser.WEBGL,
    width: 640,
    height: 480,
    backgroundColor: "black",
    physics: {
        default: "arcade",
        arcade: {
            Gravity: { x: 0, y: 0 }
        }
    },
    scene: [
        SceneMain
    ],
    roundPixels: true
};

var game = new Phaser.Game(config);

function generateRandomFood(){

}

function foodContact(player){

}

