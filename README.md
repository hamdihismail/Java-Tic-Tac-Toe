# Tic Tac Toe Game

## Project Overview
The **Tic Tac Toe Game** is a Java-based desktop application built using `javax.swing` to provide a graphical user interface (GUI). It allows users to play a classic game of Tic Tac Toe with options for single-player mode (against a computer) or multiplayer mode. The game also includes user account functionality, enabling players to register, log in, and track their win/loss records. User data is stored locally in a `JSON` file for persistence.

### Key Features
#### Game Modes:
- **Single-Player Mode:** Play against a computer with random moves.
- **Multiplayer Mode:** Play locally with another player.

#### User Management:
- **Registration:** Create a new account with a unique username and password.
- **Login:** Log in to an existing account to track game stats.
- **Guest Mode:** Play without logging in or registering.

#### Records Tracking:
- Win/loss records are stored locally for each user and displayed on the game screen.

#### Interactive Gameplay:
- **Dynamic GUI:** The game board updates in real-time with each move.
- **Win/Tie Detection:** The game announces a winner or a tie with visual feedback.
- **Computer Moves:** The computer automatically takes its turn in single-player mode.

### Technologies Used
- **Programming Language:** Java
- **GUI Framework:** javax.swing
- **Data Storage:** JSON file (locally stored)
- **Async Execution:** CompletableFuture for delayed execution (e.g., game starts, turn-taking).

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/hamdihismail/Java-Tic-Tac-Toe
   ```
2. Navigate to the project directory:
   ```bash
   cd Java-Tic-Tac-Toe
   ```
3. Run the application using the provided executable:
   ```bash
   TicTacToe.exe
   ```
### How to Play
**1. Launch the Application:**

  - Open the TicTacToe.exe file to start the game.

**2. Login or Register:**

  - **Register:** Create a new account by entering a username and password.
  - **Login:** Use existing credentials to log in and view win/loss records.
  - **Guest Mode:** Click the "Guest" button to play without an account.
    
**3. Start Playing:**

  - The game board will appear after login or guest selection.
  - In single-player mode, play as "X" while the computer plays as "O."
  - In multiplayer mode, Player 1 plays as "X," and Player 2 plays as "O."

**4. End Game:**

  - The game detects a winner or a tie and updates the records for logged-in users.
  - Restart the application to play another round.
    
### Screenshots

**Login Screen:** <br/>
<img width="252" alt="tictactoeUi" src="https://github.com/user-attachments/assets/dca2df97-3d71-4fe0-bac6-05a3534e0e08" /> <br/>
Users can register, log in, or play as a guest.

**Game Board:** <br/>
<img width="590" alt="tictactoboard" src="https://github.com/user-attachments/assets/d3b1bc17-217d-4584-b665-6cc2668e4403" /> <br/>
Interactive grid for playing Tic Tac Toe.

**Win/Tie Notification:** <br/>
<img width="587" alt="tictactoboardwin" src="https://github.com/user-attachments/assets/d9a41d95-0144-4c59-a9b7-4b11d76525ad" /> <br/>
Highlights winning moves or announces a tie.

### Project Structure

    Java-Tic-Tac-Toe-main/
        TicTacToe/
            src/
            ...
        TicTacToe.exe
        README.md
        
### Objective

This application offers a fun and engaging way to play Tic Tac Toe, with added functionality for account management and stats tracking. It is a perfect demonstration of integrating GUI design, local data storage, and Java programming.

### License

This project is licensed under the MIT License.

### Developer
  -Hamdi Ismail
