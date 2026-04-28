/**
 * Represents the game board and provides board-related utilities.
 * 
 * @author Archange DESTINE
 * @author YOUR_NAME_HERE
 */
public class Board {

    private int rows;
    private int cols;
    private int connectN;
    private int[][] boardState; // 0=empty,1=player1,2=player2

    public Board(int rows, int cols, int connectN)
            throws IllegalArgumentException {

        if (connectN <= 2 || connectN > rows || connectN > cols) {
            throw new IllegalArgumentException("Invalid connectN.");
        }

        this.connectN = connectN;
        this.rows = rows;
        this.cols = cols;
        boardState = new int[rows][cols];
    }

    public Board(int rows, int cols) {
        this(rows, cols, 4);
    }

    public Board() {
        this(6, 7, 4);
    }

    public void setRows(int rows) {
        if (rows < connectN) {
            throw new IllegalArgumentException("Rows too small.");
        }

        this.rows = rows;
        boardState = new int[this.rows][this.cols];
    }

    public void setCols(int cols) {
        if (cols < connectN) {
            throw new IllegalArgumentException("Cols too small.");
        }

        this.cols = cols;
        boardState = new int[this.rows][this.cols];
    }

    public void setConnectN(int connectN) {

        if (connectN < 3) {
            throw new IllegalArgumentException("connectN invalid.");
        }

        if (boardState != null &&
                (connectN > rows || connectN > cols)) {

            throw new IllegalArgumentException("connectN too large.");
        }

        this.connectN = connectN;
    }

    public static boolean isValidMove(int[][] boardState, int col) {

        if (boardState == null ||
                col < 0 ||
                col >= boardState[0].length) {
            return false;
        }

        return boardState[0][col] == 0;
    }

    public static int[][] copyAGivenBoardState(int[][] original) {

        int[][] copy = new int[original.length][original[0].length];

        for (int r = 0; r < original.length; r++) {
            for (int c = 0; c < original[0].length; c++) {
                copy[r][c] = original[r][c];
            }
        }

        return copy;
    }

    public static int dropPieceSimulated(int[][] b, int col, int piece) {

        if (!isValidMove(b, col)) {
            return -1;
        }

        for (int row = b.length - 1; row >= 0; row--) {
            if (b[row][col] == 0) {
                b[row][col] = piece;
                return row;
            }
        }

        return -1;
    }

    public static boolean isBoardFullForBoard(int[][] b) {

        for (int c = 0; c < b[0].length; c++) {
            if (b[0][c] == 0) {
                return false;
            }
        }

        return true;
    }

    public int[][] getArrayDeepCopy() {
        return copyAGivenBoardState(this.boardState);
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public int[][] getArray() {
        return this.boardState;
    }

    public boolean isBoardFull() {
        return isBoardFullForBoard(this.boardState);
    }
}