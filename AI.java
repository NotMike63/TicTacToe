public class AI {
    
    private static String[] currentBoard;
    private static int[] heuristicScore;

    public AI() {
        currentBoard = TicTacToe.getBoard();
        heuristicScore = new int[currentBoard.length];
    }

    public int heuristicScore() {
        for (int i = 0; i < currentBoard.length; i++) {
            if (currentBoard[i].equals(" ")) {
                heuristicScore[i] = 10;
            } else {
                heuristicScore[i] = -1;
            }
        }
        if (currentBoard[4].equals(" ")) {
            heuristicScore[4] += 50;
        }

        String firstVar;
        String middleVar;
        String lastVar;
        for (int i = 0; i < 3; i ++) { // calculates score for horizontals
            firstVar = currentBoard[3 * i];
            middleVar = currentBoard[(3 * i) + 1];
            lastVar = currentBoard[(3 * i) + 2];

            // check for horizontals heuristic score
            if (firstVar.equals(middleVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
                if (heuristicScore[(3 * i) + 2] != -1) 
                    heuristicScore[(3 * i) + 2] += 100;
            } else if (firstVar.equals(lastVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
                if (heuristicScore[(3 * i) + 1] != -1)
                    heuristicScore[(3 * i) + 1] += 100;
            } else if (lastVar.equals(middleVar) && (lastVar.equals("X") || middleVar.equals("O"))) {
                if (heuristicScore[3 * i] != -1)
                    heuristicScore[3 * i] += 100;
            }

            firstVar = currentBoard[i];
            middleVar = currentBoard[i + 3];
            lastVar = currentBoard[i + 6];

            if (firstVar.equals(middleVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
                if (heuristicScore[i + 6] != -1)
                    heuristicScore[i + 6] += 100;
            } else if (firstVar.equals(lastVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
                if (heuristicScore[i + 3] != -1)
                    heuristicScore[i + 3] += 100;
            } else if (lastVar.equals(middleVar) && (lastVar.equals("X") || middleVar.equals("O"))) {
                if (heuristicScore[i] != -1)
                    heuristicScore[i] += 100;
            }
        }
        firstVar = currentBoard[0];
        middleVar = currentBoard[4];
        lastVar = currentBoard[8];
        if (firstVar.equals(middleVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
            if (heuristicScore[8] != -1) 
                heuristicScore[8] += 100;
        } else if (firstVar.equals(lastVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
            if (heuristicScore[4] != -1)
                heuristicScore[4] += 100;
        } else if (lastVar.equals(middleVar) && (lastVar.equals("X") || middleVar.equals("O"))) {
            if (heuristicScore[0] != -1)
                heuristicScore[0] += 100;
        }

        firstVar = currentBoard[2];
        lastVar = currentBoard[6];
        if (firstVar.equals(middleVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
            if (heuristicScore[6] != -1) 
                heuristicScore[6] += 100;
        } else if (firstVar.equals(lastVar) && (firstVar.equals("X") || middleVar.equals("O"))) {
            if (heuristicScore[4] != -1)
                heuristicScore[4] += 100;
        } else if (lastVar.equals(middleVar) && (lastVar.equals("X") || middleVar.equals("O"))) {
            if (heuristicScore[2] != -1)
                heuristicScore[2] += 100;
        }

        int bestMove = 0;
        int tempMax = 0;
        for (int i = 0; i < heuristicScore.length; i ++) {
            if (heuristicScore[i] > tempMax) {
                tempMax = heuristicScore[i];
                bestMove = i;
            }
        }
        if (!currentBoard[bestMove].equals(" ")) {
            heuristicScore[bestMove] = 1;
            heuristicScore();
        }
        return bestMove;
    }
}
