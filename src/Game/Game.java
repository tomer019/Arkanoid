// 319126991 Tomer Grady
package Game;

import Collections.SpriteCollection;
import biuoop.GUI;
import java.awt.Color;
import java.util.Random;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import Collections.GameEnvironment;
import Collide.Collidable;
import Collide.Counter;
import Hit.ScoreIndicator;
import Hit.BallRemover;
import Hit.BlockRemover;
import Hit.ScoreTrackingListener;
import Shape.Point;
import Shape.Rectangle;
import Shape.Velocity;
import Sprites.Block;
import Sprites.Ball;
import Sprites.Sprite;
import Sprites.Paddle;

/**
 * The Game class initializes and runs the Arkanoid game.
 */
public class Game {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private final Counter remainingBlocks = new Counter();
    private final Counter remainingBalls = new Counter();
    private final Counter score = new Counter();
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 25;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int BALL_RADIUS = 10;
    public static final Color[] COLORS = {
            new Color(255, 87, 51),     // Coral Red
            new Color(255, 140, 0),     // Dark Orange
            new Color(255, 195, 0),     // Mustard
            new Color(0, 128, 128),     // Teal
            new Color(75, 0, 130),      // Indigo
            new Color(50, 205, 50)      // Lime Green
    };

    public static final int WALL_SIZE = 16;

    /**
     * Constructs a Game object and initializes the game environment.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * Adds a collidable to the game environment.
     *
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Removes a collidable from the game environment.
     *
     * @param c the collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Removes a sprite from the game.
     *
     * @param s the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game by creating balls, blocks, walls, paddle, death region, and score indicator.
     */
    public void initialize() {
        createBalls();
        createBlocks();
        createWalls();
        createPaddle();
        createDeathRegion();
        createScoreIndicator();
    }

    /**
     * Creates the balls for the game.
     */
    private void createBalls() {
        Ball[] balls = {
                new Ball(new Point(500, 400), BALL_RADIUS, Color.BLACK, environment),
                new Ball(new Point(400, 400), BALL_RADIUS, Color.BLACK, environment),
                new Ball(new Point(400, 400), BALL_RADIUS, Color.BLACK, environment),
        };
        Random rnd = new Random();
        for (Ball ball : balls) {
            ball.setVelocity(Velocity.fromAngleAndSpeed(rnd.nextInt(360), 5));
            ball.addToGame(this);
            remainingBalls.increase(1);
        }
    }

    /**
     * Creates the blocks for the game.
     */
    private void createBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);
        int startX = 140;
        int startY = 170;
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 12 - row; col++) {
                int x = startX + col * BLOCK_WIDTH;
                int y = startY + row * BLOCK_HEIGHT;

                Point upperLeft = new Point(x, y);
                Block block = new Block(new Rectangle(upperLeft, BLOCK_WIDTH, BLOCK_HEIGHT), COLORS[row], false);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTracker);
                block.addToGame(this);
                remainingBlocks.increase(1);
            }
            startX += BLOCK_WIDTH;
        }
    }

    /**
     * Creates the walls for the game.
     */
    private void createWalls() {
        Point upperLeftTop = new Point(0, 0);
        Block topWall = new Block(new Rectangle(upperLeftTop, SCREEN_WIDTH, WALL_SIZE), Color.GRAY, true);
        topWall.addToGame(this);

        Point upperLeftLeft = new Point(0, 0);
        Block leftWall = new Block(new Rectangle(upperLeftLeft, WALL_SIZE, SCREEN_HEIGHT), Color.GRAY, true);
        leftWall.addToGame(this);

        Point upperLeftRight = new Point(SCREEN_WIDTH - WALL_SIZE, 0);
        Block rightWall = new Block(new Rectangle(upperLeftRight, WALL_SIZE, SCREEN_HEIGHT), Color.GRAY, true);
        rightWall.addToGame(this);
    }

    /**
     * Creates the paddle for the game.
     */
    private void createPaddle() {
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(400, 575), 300, 10), Color.BLACK, 10);
        paddle.addToGame(this);
    }

    /**
     * Creates the death region for the game.
     */
    private void createDeathRegion() {
        Block region = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT + BALL_RADIUS * 2), SCREEN_WIDTH,
                1), Color.GRAY, false);
        region.addHitListener(new BallRemover(this, remainingBalls));
        addCollidable(region);
    }

    /**
     * Creates the score indicator for the game.
     */
    private void createScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(new Rectangle(new Point(0, 0), SCREEN_WIDTH, 50), score);
        scoreIndicator.addToGame(this);
    }

    /**
     * Runs the game by starting the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                break;
            } else if (remainingBalls.getValue() == 0) {
                break;
            }

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

        gui.close();
    }
}
