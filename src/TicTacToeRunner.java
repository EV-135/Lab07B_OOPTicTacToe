import javax.swing.SwingUtilities;

public class TicTacToeRunner {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            TicTacToeFrame frame = new TicTacToeFrame(game);
            frame.setVisible(true);
        });
    }
}
