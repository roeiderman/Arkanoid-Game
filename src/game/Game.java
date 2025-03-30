/**
 * @author roei derman 322467184
 */
package game;
import java.awt.Color;
import java.util.Random;

import Listeners.BallRemover;
import Listeners.BlockRemover;
import Listeners.Counter;
import Listeners.ScoreTrackingListener;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import Geometry.Rectangle;
import Geometry.Point;
import collisions.Block;
import collisions.Collidable;
import collisions.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;

/**
 * class of a Game.
 */
public class Game {
    static final int HEIGHT = 600;
    static final int WIDTH = 800;
    private final GUI gui = new GUI("Arknoid", WIDTH, HEIGHT);
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter numBlocks = new Counter(0);
    private final Counter numBalls = new Counter(0);
    private final Counter score = new Counter(0);
    private final ScoreIndicator scoreIndi = new ScoreIndicator(this.score);


    /**
     * constructor.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }

    /**
     * @param c - collidable to add
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * @param s - sprite to add
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }


    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        BlockRemover removeBlock = new BlockRemover(this, this.numBlocks);
        BallRemover removeBall = new BallRemover(this, this.numBalls);
        Color c = randomColor();
        for (int i = 1; i <= 3; i++) {
            Ball ball = new Ball(5 + (i * 5), 5 + (i * 5), 5, c);
            ball.addToGame(this);
            this.numBalls.increase(1);
            ball.setGameEnvironment(this.environment);
            c = randomColor();
        }
        for (int i = 0; i < 6; i++) {  // Create 6 lines of blocks
            for (int j = 0; j < i + 5; j++) {
                Block b = new Block(new Rectangle(new Point(740 - (j * 60),
                        200 - (i * 20)), 60, 20), c);
                b.addToGame(this);
                this.numBlocks.increase(1);
                b.addHitListener(removeBlock);
                b.addHitListener(scoreTrackingListener);
            }
            c = randomColor();
        }
        Paddle paddle = new Paddle(new Rectangle(new Point(400, HEIGHT - 50), 100, 10), gui.getKeyboardSensor());
        paddle.addToGame(this);
        Block bottomBlock = (new Block(new Rectangle(new Point(0, HEIGHT - 9), WIDTH, 10), Color.darkGray));
        bottomBlock.addToGame(this);
        bottomBlock.addHitListener(removeBall);
        this.createBorders();
        this.sprites.addSprite(this.scoreIndi);
    }

    private static Color randomColor() {
        Random random = new Random();

        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r, g, b);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (this.numBlocks.getValue() != 0 && this.numBalls.getValue() != 0) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
        if (this.numBlocks.getValue() == 0) {
            this.score.increase(100);
            gui.close();
        }
        if (this.numBalls.getValue() == 0) {
            gui.close();
        }
    }

    /**
     * create the borders of the game screen.
     */
    public void createBorders() {
        Block b1 = (new Block(new Rectangle(new Point(-1, 0), 10, HEIGHT), Color.darkGray)); // Left wall
        Block b2 = (new Block(new Rectangle(new Point(0, -1), WIDTH, 30), Color.darkGray)); // Upper wall
        Block b3 = (new Block(new Rectangle(new Point(WIDTH - 9, 0), 10, HEIGHT), Color.darkGray)); // Right wall
        b1.addToGame(this);
        b2.addToGame(this);
        b3.addToGame(this);
    }

    /**
     * remove collidable.
     * @param c - collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCol(c);
    }
    /**
     * remove sprite.
     * @param s - sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}
