
public class Game {
    private TTTBoard board;
    private String currentPlayer;
    private boolean playing;
    private boolean tie;
    private String winner;

    public Game() {
        board = new TTTBoard();
        reset();
    }

    public boolean makeMove(int row, int col) {
        if (!playing) {
            return false;
        }

        if (!board.makeMove(row, col, currentPlayer)) {
            return false;
        }

        if (board.isWin(currentPlayer)) {
            winner = currentPlayer;
            playing = false;
        } else if (board.isFull()) {
            tie = true;
            playing = false;
        } else {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }

        return true;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getMarkAt(int row, int col) {
        return board.getMarkAt(row, col);
    }

    public boolean isPlaying() {
        return playing;
    }

    public boolean isTie() {
        return tie;
    }

    public String getWinner() {
        return winner;
    }

    public void reset() {
        board.clear();
        currentPlayer = "X";
        playing = true;
        tie = false;
        winner = "";
    }
}
