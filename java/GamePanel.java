

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Represents the main panel of the game UI.
 * @author Archange DESTINE
 */
public class GamePanel extends JPanel {

    private final Engine engine;

    private final int CELL_SIZE = 80;
    private final int ROWS;
    private final int COLS;
    private final int WIDTH;
    private final int HEIGHT;

    private String winnerMessage = "";

    public GamePanel(Engine engine) {
        this.engine = engine;

        this.ROWS = engine.getBoard().getRows();
        this.COLS = engine.getBoard().getCols();
        this.WIDTH = COLS * CELL_SIZE;
        this.HEIGHT = (ROWS + 1) * CELL_SIZE;

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e);
            }
        });
    }

    private void handleClick(MouseEvent e) {
        if (engine.isGameOver() || !(engine.getCurrentPlayer() instanceof HumanPlayer)) {
            return;
        }

        int col = e.getX() / CELL_SIZE;
        if (col < 0 || col >= COLS) return;

        int row = engine.dropPiece(col, engine.getCurrentPlayerPieceColor());

        if (row != -1) {
            repaint();

            if (engine.checkWin(engine.getCurrentPlayer())) {
                engine.setGameOver(true);
                winnerMessage = "You Win!";
            } else if (engine.getBoard().isBoardFull()) {
                engine.setGameOver(true);
                winnerMessage = "It's a Tie!";
            } else {
                engine.endCurrentPlayerTurn();
                repaint();

                engine.aiMove();
                if (engine.isGameOver()) {
                    winnerMessage = engine.getBoard().isBoardFull() ? "It's a Tie!" : "AI Wins!";
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g);
        drawPieces(g);
        drawTitle(g);
        drawWinner(g);
    }

    private void drawBoard(Graphics g) {
        g.setColor(new Color(0, 100, 200));
        g.fillRect(0, CELL_SIZE, WIDTH, ROWS * CELL_SIZE);
    }

    private void drawPieces(Graphics g) {
        int[][] board = engine.getBoard().getArray();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {

                int x = c * CELL_SIZE + CELL_SIZE / 2;
                int y = (r + 1) * CELL_SIZE + CELL_SIZE / 2;

                g.setColor(Color.WHITE);
                g.fillOval(x - CELL_SIZE/2 + 8, y - CELL_SIZE/2 + 8, CELL_SIZE - 16, CELL_SIZE - 16);

                if (board[r][c] == 1) {
                    g.setColor(Color.RED);
                } else if (board[r][c] == 2) {
                    g.setColor(Color.YELLOW);
                } else continue;

                g.fillOval(x - CELL_SIZE/2 + 10, y - CELL_SIZE/2 + 10, CELL_SIZE - 20, CELL_SIZE - 20);
            }
        }
    }

    private void drawTitle(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player1 (Red) vs Player2 (Yellow)", 20, 30);
    }

    private void drawWinner(Graphics g) {
        if (!engine.isGameOver()) return;

        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, HEIGHT / 2 - 60, WIDTH, 120);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString(winnerMessage, WIDTH / 4, HEIGHT / 2);
    }
}