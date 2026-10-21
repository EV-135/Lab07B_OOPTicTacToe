
import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame {
    private static final int ROWS = 3;
    private static final int COLS = 3;

    private Game game;
    private TicTacToeTile[][] tiles =
            new TicTacToeTile[ROWS][COLS];

    public TicTacToeFrame(Game game) {
        this.game = game;

        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        JPanel boardPanel =
                new JPanel(new GridLayout(3, 3, 5, 5));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                TicTacToeTile tile =
                        new TicTacToeTile(row, col);

                tile.setFont(new Font("Arial", Font.BOLD, 48));
                tile.setFocusPainted(false);

                tile.addActionListener(event ->
                        handleMove(tile.getRow(), tile.getCol()));

                tiles[row][col] = tile;
                boardPanel.add(tile);
            }
        }

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(event -> quitGame());

        add(boardPanel, BorderLayout.CENTER);
        add(quitButton, BorderLayout.SOUTH);

        setSize(400, 450);
        setLocationRelativeTo(null);
    }

    private void handleMove(int row, int col) {
        if (!game.isPlaying()) {
            return;
        }

        if (!game.makeMove(row, col)) {
            JOptionPane.showMessageDialog(
                    this,
                    "That square is already occupied. Choose an empty square.",
                    "Invalid Move",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        updateBoard();

        if (!game.isPlaying()) {
            if (game.isTie()) {
                JOptionPane.showMessageDialog(
                        this,
                        "It's a tie!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Player " + game.getWinner() + " wins!",
                        "Game Over",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            askToPlayAgain();
        }
    }

    private void updateBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String mark = game.getMarkAt(row, col);

                tiles[row][col].setText(
                        mark.equals(" ") ? "" : mark);
            }
        }
    }

    private void askToPlayAgain() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Would you like to play another game?",
                "Play Again?",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            game.reset();
            updateBoard();

            for (int row = 0; row < ROWS; row++) {
                for (int col = 0; col < COLS; col++) {
                    tiles[row][col].setEnabled(true);
                }
            }
        } else {
            dispose();
        }
    }

    private void quitGame() {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to quit?",
                "Quit Game",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(
                    this, "Thanks for playing!");
            dispose();
        }
    }
}
