Classes:
TicTacToe.java - Handles all logic(except AI logic), handles GUI  
AI.java - Contains the logic for AI player using Hillclimbing algorithm.  
  
Important Methods:  
TicTacToe.isGoalState() - checks all possible winning combinations, returning 1 if player 1 wins, 2 if player 2 wins, 0 if a draw, and -1 if the goal state is not achieved.  
TicTacToe.TicTacToe() - constructor, initializes all graphical elements and logical elements. Graphics can be changed here.  
TicTacToe.startSolo() - begins a game against the computer, interfaces with AI.java to perform computer moves.  
TicTacToe.startRegular() - begins a regular game with 2 players.  
TicTacToe.recordResults(String fileName) - stores the results of the match in fileName within project folder.  
  
AI.heuristicScore() - calculates the heuristic score for each square and stores in heuristicScore[].  
