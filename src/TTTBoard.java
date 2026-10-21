
public class TTTBoard {
    private static final int ROWS = 3;
    private static final int COLS = 3;

    private String[][] board = new String[ROWS][COLS];

    public TTTBoard() {
        clear();
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < ROWS
                && col >= 0 && col < COLS
                && board[row][col].equals(" ");
    }

    public boolean makeMove(int row, int col, String mark) {
        if (!isValidMove(row, col)) {
            return false;
        }

        board[row][col] = mark;
        return true;
    }

    public String getMarkAt(int row, int col) {
        return board[row][col];
    }

    public boolean isWin(String mark) {
        // Check rows
        for (int row = 0; row < ROWS; row++) {
            if (board[row][0].equals(mark)
                    && board[row][1].equals(mark)
                    && board[row][2].equals(mark)) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < COLS; col++) {
            if (board[0][col].equals(mark)
                    && board[1][col].equals(mark)
                    && board[2][col].equals(mark)) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0].equals(mark)
                && board[1][1].equals(mark)
                && board[2][2].equals(mark)) {
            return true;
        }

        return board[0][2].equals(mark)
                && board[1][1].equals(mark)
                && board[2][0].equals(mark);
    }

    public boolean isFull() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals(" ")) {
                    return false;
                }
            }
        }

        return true;
    }

    public void clear() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }
}
