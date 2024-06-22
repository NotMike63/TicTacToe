import java.util.*;

public class TicTacToe
{
    private String[] board;
    int p1 = -1; //1 means x, 0 means 0
    int p2 = -1;
    private int turn;
    private int winner;
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
        if (choice.equals("x") || choice.equals("X"))
        {
            p1 = 1;
            p2 = 0;
        }
        else if(choice.equals("o") || choice.equals("O"))
        {
            p2 = 1;
            p1 = 0;
        }        
        int move = -1;
        while (!isGoalState())
        {
            System.out.println("\nTurn " + turn);
            printBoard();
            System.out.printf("\nPlayer %d's turn. \nWhere would you like to go? [0-8] ", playerTurn);
            move = input.nextInt();

            if (p1 == 1 && playerTurn == 1 || p2 == 1 && playerTurn == 2)
            {
                move("X", move);
            } else
            {
                move("O", move);
            }
            if (playerTurn == 1)
            {
                playerTurn = 2;
            } else
            {
                playerTurn = 1;
                turn++;
            }
        }
        // when goal state is reached announce the winner
        if (playerTurn == 1)
        {
            printBoard();
            System.out.println("Congratulations player 2 won!");
        } else
        {
            printBoard();
            System.out.println("Congratulations player 1 won!");
        }
    }

    public static void main(String args[])
    {
        TicTacToe game = new TicTacToe();
        game.start();
    }

    //updates current board with symbol at location
    public void move(String symbol, int location)
    {
        if (board[location].equals(" "))
        {
            board[location] = symbol;
        }
    }

    /**
     * returns true if a player has won the game.
     * @return true if game is over.
     */
    public boolean isGoalState()
    {
        for (int i = 0; i < 3; i ++)
        {
            if (board[i].equals(board[i + 3]) && board[i + 6].equals(board[i]) && !board[i].equals(" ")) //checks verticals
            {
                if (board[i].equals("X") || board[i].equals("x"))
                {
                    if (p1 == 1)
                    {
                        winner = 1;
                    } else
                    {
                        winner = 2;
                    }
                    return true;
                }
            }
            if (board[3 * i].equals(board[(3 * i) + 1]) && board[(3 * i) + 2].equals(board[3 * i]) && !board[i].equals(" ")) //checks horizontals
            {
                if (board[3 * i].equals("x") || board[3 * i].equals("X"))
                    if (p1 == 1)
                    {
                        winner = 1;
                    } else
                    {
                        winner = 2;
                    }
                    return true;
            }
        }
        if (board[2].equals(board[4]) && board[6].equals(board[2]) && !board[2].equals(" ")) //checks left Diagonal (/)
        {
            if (board[2].equals("x") || board[2].equals("X"))
            {
                if (p1 == 1)
                {
                    winner = 1;
                } else
                {
                    winner = 2;
                }
                return true;
            }
        }
        if (board[0].equals(board[4]) && board[0].equals(board[8]) && !board[0].equals(" ")) //checks right Diagonal (\)
        {
            if (board[0].equals("x") || board[0].equals("X"))
            {
                if (p1 == 1)
                {
                    winner = 1;
                } else 
                {
                    winner = 2;
                }
                return true;                
            }
        }
        return false;
    }
}