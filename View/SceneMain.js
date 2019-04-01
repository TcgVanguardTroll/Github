// Define our ES6 class (it's just a regular JavaScript object with syntactic sugar)
class SceneMain extends Phaser.Scene {
    constructor() {
        // Initialize the Phaser.Scene base class with an object containing the scene's key (just a name)
        super({ key: "SceneMain" });
    }
    

    preload() {
        // Load our images
        this.load.image("sprBackground", "Model/content/sprGrid.png");
        this.load.image("sprPlayer", "Model/content/sprPlayer.png");

            }
    

    create() {
        // Create our background as a tile sprite
        this.bg = this.add.tileSprite(0, 0, 8000, 8000, "sprBackground");

        this.physics.add.collider(this.player, this.food, function(player, food) {

        // Add your code here, the variable "player" is the instance of the 
        //    player, and "food" is the instance of food the player collided with
    }, null, this);


        // Instantiate the player
        // this.player = new Player(this.physics.add.sprite(
        //     this.game.config.width * 0.5,
        //     this.game.config.height * 0.5,
        //     "sprPlayer"
        // ))
        this.food 


        this.player  = this.physics.add.sprite(
            this.game.config.width * 0.5,
            this.game.config.height * 0.5,
            "sprPlayer"
        );

        // Set the origin of the player to the center of the sprite.
        this.player.setOrigin(0.5);

        // Ensure the player can't move out of bounds
        this.player.body.setCollideWorldBounds(true);

        // Lock the camera on the player's position
        this.cameras.main.startFollow(this.player);

        // Set the bounds
        this.physics.world.setBounds(-4000, -4000, 8000, 8000);

    }

    update() {
        
        // Calculate the angle toward the mouse (world coordinates) from the player
        var dx = this.input.activePointer.worldX - this.player.x;
        var dy = this.input.activePointer.worldY - this.player.y;

        var angle = Math.atan2(dy, dx);

        this.player.body.setVelocity(
            Math.cos(angle) * 400,
            Math.sin(angle) * 400
        );
        

    }

}