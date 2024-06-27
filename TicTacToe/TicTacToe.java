import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


// TODO
// Reorganize code 
//   - break into more functions 
//   - move code to more logical locations
//   - Fix error with x's being replaced by o's in computer

// Implement retry when incorrect input/filled space

public class TicTacToe
{
    public static String[] board;
    int p1 = 0; //1 means x, 0 means 0
    int p2 = 0;
    int game = 0;
    String p1Name;
    String p2Name;
    private String winner;
    private int turn;
    public TicTacToe()
    {
        board = new String[9];
        turn = 1;
        for (int i = 0; i < board.length; i ++)
        {
            board[i] = " ";
        }
    }

    /**
     * Prints out the board.
     */
    public void printBoard()
    {
        for (int i = 0; i < board.length; i += 3)
        {
            System.out.printf(   " %s || %s ||  %s \n", board[i], board[i + 1], board[i + 2]);
            if (i != 6)
                System.out.println(   "--------------");
        }
    }

    /**
     * Main method to play against AI.
     */
    public void startSolo(AI ai) {
        game ++;
        Scanner input = new Scanner(System.in);
        int playerTurn = 1;
        int move = -1;
        p2Name = "Computer";
        setPlayerSymbols(input);
        while (isGoalState() == -1) {

            if (playerTurn == 1) {
                System.out.println("\nTurn " + turn);            
                printBoard();    
                System.out.printf("\nPlayer %d's turn. \nWhere would you like to go? [0-8] ", playerTurn);
                move = Integer.parseInt(input.nextLine());   

                // forces valid input
                while (!(move < 9 && move > -1 && board[move].equals(" "))) {
                    System.out.println(move);
                    System.out.println("Please input a number 0 through 8 located at an empty space");
                    move = Integer.parseInt(input.nextLine());     
                }          
                if (p1 == 1) {
                    board[move] = "X";
                } else if (p1 == 0){
                    board[move] = "O";
                }
                playerTurn = 2;
            } else { // AI's turn 
                printBoard();
                move = ai.heuristicScore();
                System.out.println("Computer's move " + move);
                if (p2 == 1 && board[move].equals(" ")) {
        //            System.out.println("Was blank and AI X");
                    board[move] = "X";
                } else if (board[move].equals(" ")){
        //            System.out.println("Was blank and AI O");
                    board[move] = "O";
                }
                playerTurn = 1;
                turn++;
            }
        }
        // when goal state is reached announce the winner
        printBoard();
        int finalResult = isGoalState();
        if (finalResult == 1) {
            System.out.println("Congratulations player 1 wins!");
            winner = "Player 1";
        } else if (finalResult == 2) {
            System.out.println("Congratulations Computer wins!");
            winner = "Computer";
        } else if (finalResult == 0) {
            winner = "Draw";
            System.out.println("Oh no it's a draw.");
        }
        System.out.println("Would you like to log this match? (Yes/No) ");
        if (input.nextLine().equals("Yes")){
            recordResults("Results.txt");
        }
        System.out.println("Would you like to play again? (Yes/No) ");
        String playAgain = input.nextLine();
        while (!playAgain.equals("Yes") && !playAgain.equals("No")) {
            System.out.println("Please select Yes or No: ");
            if (input.hasNextLine()) {
                playAgain = input.nextLine();
            }
        }
        if (playAgain.equals("Yes")) {
            reset();
            turn = 1;
            startSolo(ai);

        } else if (playAgain.equals("No")) {
            System.out.println("Thank you for playing!");
        }      
    }

    /**
     * Main method to play regular.
     */
    public void startRegular() 
    {
        game ++;
        Scanner input = new Scanner(System.in);
        int playerTurn = 1;
        setPlayerSymbols(input);
        System.out.println("Player 2 what is your name? ");
        p2Name = input.nextLine();
        Integer move = -1;
        while (isGoalState() == -1) {
            if (playerTurn == 1) {
                System.out.println("\nTurn " + turn);                
            }

            printBoard();

            System.out.printf("\nPlayer %d's turn. \nWhere would you like to go? [0-8] ", playerTurn);
            move = Integer.parseInt(input.nextLine());   

            // forces valid input
            while (!(move < 9 && move > -1 && board[move].equals(" "))) {
                System.out.println(move);
                System.out.println("Please input a number 0 through 8 located at an empty space");
                move = Integer.parseInt(input.nextLine());     
            }          
            if (playerTurn == 1) {
                if (playerTurn == 1)  { // player 1's turn
                    if (p1 == 1) {
                        board[move] = "X";
                    } else {
                        board[move] = "O";
                    }
                    playerTurn = 2;
                }                              
            } else if (playerTurn == 2){ // player 2's turn 
                if (p2 == 1) {
                    board[move] = "X";
                } else {
                    board[move] = "O";
                }
                playerTurn = 1;
                turn++;
            }
        }
        // when goal state is reached announce the winner
        printBoard();
        int finalResult = isGoalState();
        if (finalResult == 1) {
            System.out.println("Congratulations player 1 wins!");
            winner = "Player 1";
        } else if (finalResult == 2) {
            System.out.println("Congratulations Player 2 wins!");
            winner = "Player 2";
        } else if (finalResult == 0) {
            winner = "Draw";
            System.out.println("Oh no it's a draw.");
        }
        System.out.println("Would you like to log this match? (Yes/No) ");
        if (input.nextLine().equals("Yes")){
            recordResults("Results.txt");
        }
        System.out.println("Would you like to play again? (Yes/No) ");
        String playAgain = input.nextLine();
        while (!playAgain.equals("Yes") && !playAgain.equals("No")) {
            System.out.println("Please select Yes or No: ");
            if (input.hasNextLine()) {
                playAgain = input.nextLine();
            }
        }
        if (playAgain.equals("Yes")) {
            reset();
            turn = 1;
            startRegular();
        } else if (playAgain.equals("No")) {
            System.out.println("Thank you for playing!");
        }      
    }

    /**
     * Returns the current board state.
     * @return board.
     */
    public static String[] getBoard() {
        return board;
    }

    /**
     * Returns status of the goal state.
     * @return 1 if p1 wins, 2 if p2 wins, 0 if draw, -1 if not goal state.
     */
    public int isGoalState()
    {
        for (int i = 0; i < 3; i ++)
        {
            if (board[i].equals(board[i + 3]) && board[i + 6].equals(board[i]) && !board[i].equals(" ")) //checks verticals
            {
                if (board[i].equals("X") && board[i+3].equals("X") && board[i+6].equals("X")) {
                    if (p1 == 1) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else if (board[i].equals("O") && board[i+3].equals("O") && board[i+6].equals("O")) {
                    if (p1 == 0) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
            if (board[3 * i].equals(board[(3 * i) + 1]) && board[(3 * i) + 2].equals(board[3 * i]) && !board[3 * i].equals(" ")) //checks horizontals
            {
                if (board[3 * i].equals("X") && board[(3*i) + 1].equals("X") && board[(3*i) + 2].equals("X")) {
                    if (p1 == 1) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else if (board[3 * i].equals("O") && board[(3 * i) + 1].equals("O") && board[(3 * i) + 2].equals("O")) {
                    if (p1 == 1) {
                        return 1;
                    } else{
                        return 2;
                    }

                }           
            }
        }


        if (board[2].equals(board[4]) && board[6].equals(board[2]) && !board[2].equals(" ")) //checks left Diagonal (/)
        {
            if (board[2].equals("X") && board[4].equals("X") && board[6].equals("X")) // checks to see if player 1 is winner
            {
                if (p1 == 1) {
                    return 1;
                } else if (p2 == 1) { //checks to see if player 2 is winner
                    return 2;
                }
            } else if (board[2].equals("O") && board[4].equals("O") && board[6].equals("O")) {
                if (p1 == 0) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }
        if (board[0].equals(board[4]) && board[0].equals(board[8]) && !board[0].equals(" ")) //checks right Diagonal (\)
        {
            if (board[0].equals("X") && board[4].equals("X") && board[8].equals("X")) {
                if (p1 == 1) {
                    return 1;
                } else {
                    return 2;
                }
          
            } else if (board[0].equals("O") && board[4].equals("O") && board[8].equals("O")) {
                if (p1 == 0) {
                    return 1;
                } else {
                    return 2;
                }
            }              
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i].equals(" ")){
                return -1; // continue execution of the main loop
            }
        }
        return 0; // stop execution of main loop to indicate draw
    }

    private void setPlayerSymbols(Scanner input) {
        try {
            System.out.println("Player 1 what is your name? ");
            if (input.hasNextLine()) {
                p1Name = input.nextLine();
            }            
        } catch (InputMismatchException e) {
            while (input.hasNextLine()) {
                System.out.println("Please use a valid name: ");  
                p1Name = input.nextLine();              
            }
        }

        System.out.println("Does Player 1 want X's or O's? ");
        String choice = input.nextLine();
        while (!choice.matches("[oOxX]")) {
            System.out.println("Please select 'x', 'X', 'o', or 'O'.");
            if (input.hasNextLine()) {
                choice = input.nextLine();
            }
        }
        if (choice.equalsIgnoreCase("x")) {
            p1 = 1;
            p2 = 0;
        } else {
            p1 = 0;
            p2 = 1;
        }
    }

    public void reset() {
        for (int i = 0; i < board.length; i ++) {
            board[i] = " ";
        }
    }


    /**
     * This method stores the results of matches long term in a file.
     * @param fileName File you wish the results to be written to.
     */
    public void recordResults(String fileName) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        String formattedString = String.format("Game %d: %s   %s", game, winner, formattedDate);            


        File file = new File(fileName);
        boolean isNewFile = !file.exists() || file.length() == 0; // Check if file is empty

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            if (isNewFile) {
                writer.println("Game    Winner   Date");
            }
            writer.println(formattedString);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        Integer gameMode = -1;
        AI ai = new AI();           
        System.out.println("Would you like to play with 1 or 2 players?");
        while (gameMode != 1 && gameMode != 2) {
            if (in.hasNextLine()) {
                gameMode = in.nextInt();            
            }
            if (gameMode == 1) {
                game.startSolo(ai);
            } else if (gameMode == 2) {
                game.startRegular();
            }
       } 
    }
}