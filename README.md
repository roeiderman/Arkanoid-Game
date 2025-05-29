# Arkanoid-Game
## 📚 Description
This project is an implementation of the classic **Arkanoid** (brick breaker) game developed in Java as part of an OOP course project in Bar Ilan University.
The game features a GUI, score tracking and file management.

## 🔗 Table of Contents
- [📚 Description](#-description)
- [🧩 Main Features](#-main-features)
- [💻 Installing & Running](#-installing--running)
- [🎮 Controls](#-controls)
- [👨‍🏫 Author](#-author)


## 🧩 Main Features
1. GUI: Built using Java and the biuoop-1.4.jar given by the courese's lecturer.
2. Game over and win conditions.
3. File Management: scores are saved in json file.
4. Design Patterns:
   - Singleton: Managing global game settings and resources. (e.g. screen dimensions and game resources)
   - Observer: Used in event listening between game components. especially used for hit detection between the ball and the blocks as well as score updates.
   - Command: For executing actions related to menu selections.

## Installing & Running
### ✅ Requirements
- Java JDK (8 or later)
- `biuoop-1.4.jar` (should be in the project folder)

### ▶️ Steps (Windows)
1. Clone the repository:
    ```git clone https://github.com/roeiderman/Arkanoid-Game.git```
2. Navigate to the project directory:
    ```cd Arkanoid-Game```
3. Compile the game:
    ```javac -cp biuoop-1.4.jar -d out src\Ass5Game.java src\game\*.java src\collisions\*.java src\Geometry\*.java src\Listeners\*.java src\sprites\*.java```
4. Run the game:
    ```java -cp "java -cp "biuoop-1.4.jar;out" Ass5Game```

## 🎮 Controls
- Left Arrow / A – Move paddle left
- Right Arrow / D – Move paddle right
- Spacebar – Start or resume ball movement
- Esc – Exit game

## 👨‍🏫 Author
- Roey Derman
- Course: Object-Oriented Programming
- Institution: Bar-Ilan University
