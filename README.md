<h1 align="center">🎮 ARKANOID GAME</h1>
<h3 align="center">Assignment 5 — Object-Oriented Programming (OOP) Course | Bar-Ilan University</h3>

---

## 🧩 Overview
This project is an implementation of the classic **Arkanoid (Breakout)** game written in **Java**.  
It was developed as part of the * Object-Oriented Programming (OOP)* course and uses the **biuoop** library for GUI, drawing, and keyboard input.

---

## 🚀 Features
- Interactive paddle controlled by keyboard (`←` / `→`)
- Bouncing balls with realistic collision physics  
- Colorful blocks that disappear on hit  
- Score tracking and display  
- Game-over and win conditions  

---

## 🛠️ Technologies
- **Java 8+**
- **Ant** (build automation)
- **biuoop-1.4.jar** (GUI and input library)

---

## 📂 Project Structure
```
ass5/
│
├── build.xml # Ant build configuration
├── lib/
│ └── biuoop-1.4.jar # External library for GUI
├── src/
│ ├── Game/ # Main game logic
│ ├── Sprites/ # Game objects (Ball, Paddle, Block, etc.)
│ ├── Collide/ # Collision handling
│ ├── Hit/ # Hit listeners and score tracking
│ ├── Shape/ # Geometry classes (Point, Rectangle, etc.)
│ └── Collections/ # Sprite and collidable collections
└── bin/ # Compiled class files
```


---

## ▶️ Run Instructions
1. Make sure you have **Java** and **Ant** installed.  
2. Navigate to the project folder:
   ```bash
   cd C:\dev\ass5
Compile the project:
 ```bash
ant compile
 ```

Run the game:
 ```bash
ant run
 ```

## 🎮 Controls
 | Key | Action                         |
| --- | ------------------------------ |
| ←   | Move paddle left               |
| →   | Move paddle right              |
| ESC | Exit the game (if implemented) |


## 🧠 Author

Tomer Grady
Bar-Ilan University
 Object-Oriented Programming (OOP) Course — Assignment 5
⭐ If you liked this project, consider giving it a star on GitHub!
