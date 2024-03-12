/* Project Made by Recio's Group for PRELIM GROUP RESEARCH PROJECT CCS103 COMPUTER PROGRAMMING 2

Group Members:

Joaquin Aaron P. Recio - Lead Programmer
Anne Galzeleigh L. Eliang - Co-Lead Programmer
Kirsten Joi A. Lazarte - Member 
Jaymart G. Impas - Member
John Henrics L. Mateo - Member
Brenda Lazado - Member
Jennelyn N. Magtibay - Member
Marc Alvin P. Quitorio - Member
Kim Francis O. Dela Cruz - Member
Julius L. Natividad - Member

*/ 

import java.util.Scanner;

public class RecioGroupBingoLineUpGame {
    // Main Method
    public static void main(String[] args) {
        // Final Output
        RecioGroupBingoLineUpGame bingoLineUpGame = new RecioGroupBingoLineUpGame();
        bingoLineUpGame.playGame();
    }

    /* This defines constants for a 6x7 game board where '-' represents an empty slot, 'X' represents Player 1's piece, and 'O' represents Player 2's piece. */
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY_SLOT = '-';
    private static final char PLAYER1_PIECE = 'X'; // Mark for Player 1
    private static final char PLAYER2_PIECE = 'O'; // Mark for Player 2

    // Declaring a private 2D array of characters
    private char[][] board;

    /* This code initializes the board as a 2D array of characters with the dimensions ROWS and COLUMNS, and then calls the initializeBoard() method. */
    public RecioGroupBingoLineUpGame() {
        board = new char[ROWS][COLUMNS];
        initializeBoard();
    }

    /* The code uses nested loops to iterate through the rows and columns of the board, setting each slot to be empty. */
    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY_SLOT;
            }
        }
    }

    /* Defining a method that iterates through a 2D array called board and prints its contents to the console, row by row. */
    private void displayBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /* This code checks if a move is valid in a game. It takes an integer parameter column and returns true if the column is within the valid range and the top row of the board at that column is empty. */
    private boolean isValidMove(int column) {
        return column >= 0 && column < COLUMNS && board[0][column] == EMPTY_SLOT;
    }

    /* This Method places a game piece in the specified column of the game board. It iterates over the rows of the board starting from the bottom, and places the piece in the first empty slot it finds. */
    private void makeMove(int column, char piece) {
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == EMPTY_SLOT) {
                board[i][column] = piece;
                break;                
            }
        }
    }

    private boolean checkWin(char piece) {
        /* 
         * Checking for horizontal, vertical, and diagonal patterns
         * (Checking only the necessary rows and columns to minimize the following code)
         */

        // Checking for horizontal patterns
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == piece && board[i][j + 1] == piece && board[i][j + 2] == piece && board[i][j + 3] == piece) {
                    return true;
                }
            }
        }

        // Checking for vertical patterns
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == piece && board[i + 1][j] == piece && board[i + 2][j] == piece && board[i + 3][j] == piece) {
                    return true;
                }
            }
        }

        // Checking for diagonal patterns (from top-left to bottom-right)
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = 0; j < COLUMNS - 3; j++) {
                if (board[i][j] == piece && board[i + 1][j + 1] == piece && board[i + 2][j + 2] == piece && board[i + 3][j + 3] == piece) {
                    return true;
                }
            }
        }

        // Checking for diagonal patterns (from top-right to bottom-left)
        for (int i = 0; i < ROWS - 3; i++) {
            for (int j = COLUMNS - 1; j >= 3; j--) {
                if (board[i][j] == piece && board[i + 1][j - 1] == piece && board[i + 2][j - 2] == piece && board[i + 3][j - 3] == piece) {
                    return true;
                }
            }
        }

        return false;
    }

    public void playGame() {
        /* The following code reads an input from the console and it also initializes 2 boolean variables, player1Turn and gameWon, to true and false respectively. */
        Scanner sc = new Scanner(System.in);
        boolean player1Turn = true;
        boolean gameWon = false;

        /* This code creates an infinite loop that will keep the game running until the player wins or the board is full. */
        while (true) {
            System.out.println("\n");
            System.out.println("Welcome to Recio's Group Bingo Line Up Game!");
            System.out.println("\n");
            System.out.println("MENU: ");
            System.out.println("1." + " PLAY THE GAME");
            System.out.println("2." + " EXIT");
            System.out.println("\n");
            System.out.print("Select '1' to PLAY or '2' to EXIT: ");

            if (sc.hasNextInt()) {
                int choice = sc.nextInt();

                if (choice == 1) {
                    // Reset the board for a new game
                    initializeBoard();

                    while (true) {
                        // Calling the function displayBoard(), then determines the current player's piece based on player1Turn flag, prompts the player to enter a column number, and reads the input as an integer.
                        System.out.print("\n");
                        displayBoard();
                        System.out.print("\n");
                        char currentPiece = (player1Turn) ? PLAYER1_PIECE : PLAYER2_PIECE;
                        System.out.print("Player " + ((player1Turn) ? "1" : "2") + "'s turn (Enter column from 0-6): ");

                        if (sc.hasNextInt()) {
                            int playerMove = sc.nextInt();
                            
                            // This code checks if the player's move is valid. If it is, it makes the move, checks if the game is won, and then switches to the other player's turn. If the move is not valid, it prints a message asking the player to try again.
                            if (isValidMove(playerMove)) {
                                makeMove(playerMove, currentPiece);
                                gameWon = checkWin(currentPiece);
                                player1Turn = !player1Turn;
                            } else {
                                System.out.println("\n");
                                System.out.println("Invalid move. Please try again.");
                            }
    
                            // Check if the game is won by a player
                            if (gameWon) {
                                displayBoard();
                                System.out.println("\n");
                                System.out.println("Player " + ((player1Turn) ? "2" : "1") + " WINS!");
                                break;
                            }
    
                            // Check for a draw
                            boolean boardFull = true;
                            for (int i = 0; i < COLUMNS; i++) {
                                if (board[0][1] == EMPTY_SLOT) {
                                    boardFull = false;
                                    break;
                                }
                            }
    
                            // Statement when the gameplay is a draw
                            if (boardFull) {
                                displayBoard();
                                System.out.println("\n");
                                System.out.println("The match-up is a draw!");
                                break;
                            }
                        } else {
                            System.out.println("\n");
                            // Statement when the user input is not an integer
                            System.out.println("Invalid input. Please enter a number for the column.");
                            sc.next(); // Consuming the invalid input
                        }
                    }
                } else if (choice == 2) {
                    System.out.println("\n");
                    // Statement when the user input is 2
                    System.out.println("Leaving the game. Thank you for playing!");
                    break;
                } else {
                    System.out.println("\n");
                    // Statement when the user input is not 1 or 2 at the main menu
                    System.out.println("Invalid choice. Please enter 1 to PLAY or 2 to EXIT.");
                }
            } else {
                System.out.println("\n");
                // Statement when the user input is not an integer
                System.out.println("Invalid input. Please enter a number.");
                sc.next(); // Consuming the invalid input
            }
        }

        sc.close();
    }
}