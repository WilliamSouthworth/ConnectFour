/**
 * The game engine that manages turns, validates moves, and implements the AI
 * logic.
 * 
 * @author Archange DESTINE
 * @author YOUR_NAME_HERE
 */
public class Engine {

    private Player player1;
    private Player player2;
    private Board board;
    private boolean gameOver;
    private int connectN;
    private Player currentPlayer;
    private DifficultyLevel difficultyLevel;
    private long movesAnalized;

    public Engine(
            Player player1,
            Player player2,
            int rows,
            int cols,
            int connectN,
            DifficultyLevel difficultyLevel) {

        if (player1 == null || player2 == null || difficultyLevel == null) {
            throw new IllegalArgumentException("Players and difficulty level cannot be null.");
        }

        boolean player1Human = player1 instanceof HumanPlayer;
        boolean player2Human = player2 instanceof HumanPlayer;
        boolean player1Computer = player1 instanceof ComputerPlayer;
        boolean player2Computer = player2 instanceof ComputerPlayer;

        if (!((player1Human && player2Computer) || (player1Computer && player2Human))) {
            throw new IllegalArgumentException("One player must be human and one player must be computer.");
        }

        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board(rows, cols, connectN);
        this.connectN = connectN;
        this.difficultyLevel = difficultyLevel;
        this.currentPlayer = player1;
        this.gameOver = false;
        this.movesAnalized = 0;
    }

    public Engine(Player player1, Player player2, int rows, int cols, int connectN) {
        this(player1, player2, rows, cols, connectN, DifficultyLevel.MEDIUM);
    }

    public Engine(Player player1, Player player2, int rows, int cols) {
        this(player1, player2, rows, cols, 4);
    }

    public Engine(Player player1, Player player2) {
        this(player1, player2, 6, 7, 4);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getCurrentPlayerPieceColor() {
        return (currentPlayer == player1) ? 1 : 2;
    }

    public int getHumanPlayerPieceColor() {
        return (player1 instanceof HumanPlayer) ? 1 : 2;
    }

    public int getComputerPlayerPieceColor() {
        return (player1 instanceof HumanPlayer) ? 2 : 1;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void endCurrentPlayerTurn() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public int dropPiece(int col, int piece) {
        return Board.dropPieceSimulated(board.getArray(), col, piece);
    }

    private boolean isTerminal(int[][] b) {
        return checkWinForBoardState(b, player1)
                || checkWinForBoardState(b, player2)
                || Board.isBoardFullForBoard(b);
    }

    public Board getBoard() {
        return board;
    }

    private boolean checkWinForBoardState(int[][] b, Player player) {

        int piece = (player == player1) ? 1 : 2;

        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {

                if (b[r][c] == piece) {

                    if (checkDirection(b, r, c, 0, 1, piece)) {
                        return true;
                    }

                    if (checkDirection(b, r, c, 1, 0, piece)) {
                        return true;
                    }

                    if (checkDirection(b, r, c, 1, 1, piece)) {
                        return true;
                    }

                    if (checkDirection(b, r, c, 1, -1, piece)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean checkDirection(
            int[][] b,
            int row,
            int col,
            int rowChange,
            int colChange,
            int piece) {

        for (int i = 0; i < connectN; i++) {
            int r = row + i * rowChange;
            int c = col + i * colChange;

            if (r < 0 || r >= board.getRows() || c < 0 || c >= board.getCols()) {
                return false;
            }

            if (b[r][c] != piece) {
                return false;
            }
        }

        return true;
    }

    public void aiMove() {

        int AIPiece = (player1 instanceof ComputerPlayer) ? 1 : 2;
        Player humanPlayer = (player1 instanceof ComputerPlayer) ? player2 : player1;
        Player aiPlayer = (player1 instanceof ComputerPlayer) ? player1 : player2;

        if (gameOver) {
            return;
        }

        int depth = switch (difficultyLevel) {
            case EASY -> 2;
            case MEDIUM -> 3;
            case HARD -> 4;
            case IMPOSSIBLE -> 5;
        };

        movesAnalized = 0;
        System.out.println("Move analized reset : " + movesAnalized);

        int bestCol = findBestMove(
                board.getArray(),
                depth,
                Integer.MIN_VALUE,
                Integer.MAX_VALUE,
                true)[0];

        System.out.println("Move analized : " + movesAnalized);

        if (bestCol != -1) {
            dropPiece(bestCol, AIPiece);

            if (checkWin(aiPlayer)) {
                gameOver = true;
            } else if (board.isBoardFull()) {
                gameOver = true;
            } else {
                currentPlayer = humanPlayer;
            }
        }
    }

    public boolean checkWin(Player player) {
        return checkWinForBoardState(board.getArray(), player);
    }

    public int[] findBestMove(int[][] boardState, int depth, boolean maximizing) {
        movesAnalized += 1;

        int AI = (player1 instanceof ComputerPlayer) ? 1 : 2;
        int PLAYER = (player1 instanceof ComputerPlayer) ? 2 : 1;

        if (depth == 0 || isTerminal(boardState)) {
            return new int[] { -1, evaluateBoardState(boardState) };
        }

        int bestCol = -1;

        if (maximizing) {
            int maxEval = Integer.MIN_VALUE;

            for (int col = 0; col < board.getCols(); col++) {
                if (Board.isValidMove(boardState, col)) {
                    int[][] tempBoard = Board.copyAGivenBoardState(boardState);
                    Board.dropPieceSimulated(tempBoard, col, AI);

                    int eval = findBestMove(tempBoard, depth - 1, false)[1];

                    if (eval > maxEval) {
                        maxEval = eval;
                        bestCol = col;
                    }
                }
            }

            return new int[] { bestCol, maxEval };

        } else {
            int minEval = Integer.MAX_VALUE;

            for (int col = 0; col < board.getCols(); col++) {
                if (Board.isValidMove(boardState, col)) {
                    int[][] tempBoard = Board.copyAGivenBoardState(boardState);
                    Board.dropPieceSimulated(tempBoard, col, PLAYER);

                    int eval = findBestMove(tempBoard, depth - 1, true)[1];

                    if (eval < minEval) {
                        minEval = eval;
                        bestCol = col;
                    }
                }
            }

            return new int[] { bestCol, minEval };
        }
    }

    public int[] findBestMove(
            int[][] boardState,
            int depth,
            int alpha,
            int beta,
            boolean maximizing) {

        movesAnalized += 1;

        int AI = (player1 instanceof ComputerPlayer) ? 1 : 2;
        int PLAYER = (player1 instanceof ComputerPlayer) ? 2 : 1;

        if (depth == 0 || isTerminal(boardState)) {
            return new int[] { -1, evaluateBoardState(boardState) };
        }

        int bestCol = -1;

        if (maximizing) {
            int maxEval = Integer.MIN_VALUE;

            for (int col = 0; col < board.getCols(); col++) {
                if (Board.isValidMove(boardState, col)) {
                    int[][] tempBoard = Board.copyAGivenBoardState(boardState);
                    Board.dropPieceSimulated(tempBoard, col, AI);

                    int eval = findBestMove(
                            tempBoard,
                            depth - 1,
                            alpha,
                            beta,
                            false)[1];

                    if (eval > maxEval) {
                        maxEval = eval;
                        bestCol = col;
                    }

                    alpha = Math.max(alpha, eval);

                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            return new int[] { bestCol, maxEval };

        } else {
            int minEval = Integer.MAX_VALUE;

            for (int col = 0; col < board.getCols(); col++) {
                if (Board.isValidMove(boardState, col)) {
                    int[][] tempBoard = Board.copyAGivenBoardState(boardState);
                    Board.dropPieceSimulated(tempBoard, col, PLAYER);

                    int eval = findBestMove(
                            tempBoard,
                            depth - 1,
                            alpha,
                            beta,
                            true)[1];

                    if (eval < minEval) {
                        minEval = eval;
                        bestCol = col;
                    }

                    beta = Math.min(beta, eval);

                    if (beta <= alpha) {
                        break;
                    }
                }
            }

            return new int[] { bestCol, minEval };
        }
    }

    private int scoreWindows(int[][] b, int piece) {

        int score = 0;
        int opp = (piece == 1) ? 2 : 1;

        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols() - connectN + 1; c++) {
                int[] line = new int[connectN];

                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r][c + i];
                }

                score += evaluateLine(line, piece, opp);
            }
        }

        for (int c = 0; c < board.getCols(); c++) {
            for (int r = 0; r < board.getRows() - connectN + 1; r++) {
                int[] line = new int[connectN];

                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r + i][c];
                }

                score += evaluateLine(line, piece, opp);
            }
        }

        for (int r = 0; r < board.getRows() - connectN + 1; r++) {
            for (int c = 0; c < board.getCols() - connectN + 1; c++) {
                int[] line = new int[connectN];

                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r + i][c + i];
                }

                score += evaluateLine(line, piece, opp);
            }
        }

        for (int r = 0; r < board.getRows() - connectN + 1; r++) {
            for (int c = connectN - 1; c < board.getCols(); c++) {
                int[] line = new int[connectN];

                for (int i = 0; i < connectN; i++) {
                    line[i] = b[r + i][c - i];
                }

                score += evaluateLine(line, piece, opp);
            }
        }

        return score;
    }

    private long evaluateLine(int[] line, int piece, int opp) {
        long score = 0;
        int countPiece = 0;
        int countOpp = 0;
        int countEmpty = 0;

        for (int cell : line) {
            if (cell == piece) {
                countPiece++;
            } else if (cell == opp) {
                countOpp++;
            } else {
                countEmpty++;
            }
        }

        int diff = countPiece - countOpp;

        if (countOpp == 0) {
            score += Math.pow(10, countPiece - countEmpty);
        } else {
            if (countPiece > countOpp) {
                score += Math.pow(10, diff);
            } else if (countOpp > countPiece) {
                score -= Math.pow(10, -diff);
            }
        }

        return score;
    }

    private int evaluateBoardState(int[][] b) {

        int computerPiece = player1 instanceof ComputerPlayer ? 1 : 2;
        int humanPiece = player1 instanceof ComputerPlayer ? 2 : 1;
        int score = 0;

        for (int row = 0; row < board.getRows(); row++) {
            if (b[row][board.getCols() / 2] == computerPiece) {
                score += 4;
            }
        }

        score += scoreWindows(b, computerPiece) - scoreWindows(b, humanPiece);

        return score;
    }
}