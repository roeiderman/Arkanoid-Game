import game.Game;
/**
 * @author roei derman 322467184
 */
public class Ass5Game {
    /**
     * Create a game object, initializes and runs it.
     * The game should include a paddle (which is controlled by the left and right arrows), two balls, and
     * blocks.
     *
     * @param args - args
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

