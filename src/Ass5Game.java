//319126991 Tomer Grady
import Game.Game;

/**
 * The Ass3Game class contains the main method to initialize and run the game.
 */
public class Ass5Game {

    /**
     * The main method initializes the game and starts the game loop.
     *
     * @param args command line arguments (not used).
     */
    public static void main(String[] args) {
        // Create a new Game.Game object
        Game game = new Game();

        // Initialize the game (set up the game environment, paddle, balls, and blocks)
        game.initialize();

        // Run the game (start the game loop)
        game.run();
    }
}
