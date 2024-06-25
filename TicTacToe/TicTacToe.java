import java.util.*;


// TODO
// Reorganize code 
//   - break into more functions 
//   - move code to more logical locations

// Implement retry when incorrect input/filled space

public class TicTacToe
{
    private String[] board;
    int p1 = 0; //1 means x, 0 means 0
    int p2 = 0;
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

    public void start() 
    {
        Scanner input = new Scanner(System.in);
        int playerTurn = 1;
        System.out.println("Does Player 1 want x's or o's? ");
        String choice = input.nextLine(); 
        if (!choice.matches("[oOxX]")) {
            do {
                System.out.println("Please select 'x', 'X', 'o', or 'O'.");
                if (input.hasNextLine()) {
                    choice = input.nextLine();
                    if (choice.equals("x") || choice.equals("X")) {
                        p1 = 1;
                    }
                    else if(choice.equals("o") || choice.equals("O")) {
                        p2 = 1;
                    }
                }
            } while(!choice.matches("[oOxX]"));
        }

        Integer move = -1;

        while (isGoalState() == -1) {
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

            if (playerTurn == 1)  { // player 1's turn
                if (p1 == 1) {
                    board[move] = "X";
                } else {
                    board[move] = "O";
                }
                playerTurn = 2;
            } else { // player 2's turn 
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
        } else if (finalResult == 2) {
            System.out.println("Congratulations player 2 wins!");
        } else if (finalResult == 0) {
            System.out.println("Oh no it's a draw.");
        }
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

    public static void main(String args[])
    {
        TicTacToe game = new TicTacToe();
        game.start();
        System.out.println("Thank you for playing");
    }
}