🎮 Arkanoid Game (Assignment 5)

This project is an implementation of the classic Arkanoid (Breakout) game written in Java, as part of the Advanced Programming course.
It uses the biuoop library for GUI, drawing, and keyboard handling.

🧩 Features

Interactive paddle controlled by the keyboard (← and → keys).

Dynamic bouncing balls with adjustable velocity.

Multiple colorful blocks that disappear upon collision.

Score tracking and display.

Game over and victory logic.

🛠️ Technologies

Java 8+

Ant (for build and run)

biuoop-1.4.jar (for graphics and input)

📦 Project Structure
ass5/
│
├── build.xml              # Ant build file
├── lib/
│   └── biuoop-1.4.jar     # External library for GUI
├── src/
│   ├── Game/              # Main game logic
│   ├── Sprites/           # Game objects (Ball, Paddle, Block, etc.)
│   ├── Collide/           # Collision handling
│   ├── Hit/               # Hit listeners and score tracking
│   ├── Shape/             # Geometry classes (Point, Rectangle, etc.)
│   └── Collections/       # Sprite and collidable collections
└── bin/                   # Compiled class files

▶️ How to Run

Make sure you have Java and Ant installed.

Navigate to the project directory:

cd C:\dev\ass5


Build the project:

ant compile


Run the game:

ant run

🕹️ Controls

← / → — Move the paddle left and right

ESC — Exit the game (if implemented)

👨‍💻 Author

Tomer Grady
Bar-Ilan University — Advanced Programming Course (Assignment 5)
