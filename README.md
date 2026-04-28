# Connect-N (Java)

A Java implementation of **Connect-N** (a generalized version of Connect Four) built as an Object-Oriented Programming project.

## Features

- Configurable board dimensions and win conditions (Connect-N)
- Human vs Computer gameplay
- AI opponent using Minimax with Alpha-Beta Pruning
- Multiple AI difficulty levels
- Custom data structures:
  - MyArrayList
  - MyIterator
- Recursive algorithms and game logic
- Java GUI with player avatars

## Concepts Used

- Object-Oriented Programming
- Inheritance and Interfaces
- Recursion
- Custom Data Structures
- Game AI (Minimax)
- Alpha-Beta Pruning

## Files

```text
MyArrayList.java
MyIterator.java
Utility.java
Player.java
HumanPlayer.java
ComputerPlayer.java
Board.java
Engine.java
ConnectNUI.java
```

## Running the Game

Compile:

```bash
javac *.java
```

Run:

```bash
java ConnectNUI
```

## AI Opponent

The computer player uses:

- Recursive Minimax search
- Alpha-Beta pruning
- Heuristic board evaluation
- Difficulty-based search depth

## Future Improvements

- Improved AI heuristics
- GUI polish
- Multiplayer support
- Additional optimizations

## Author

William Southworth
