import javax.swing.*;
import java.awt.*;

/**
 * The graphical user interface related classes for playing the game.
 * Run ConnectNUI to play once all classes are complete.
 * @author Archange DESTINE
 */
public class ConnectNUI extends JFrame {

    //we want to highligh the player's avatar when it's their turn, 
    //and the AI's avatar when it's the AI's turn.
    // We can do this by passing the player names and avatars to the side panel,
    // and then updating the side panel whenever the turn changes.

    SidePanel sidePanel;
    GamePanel gamePanel;

    private Engine engine;

    public ConnectNUI(PlayerSetup.SetupResult setup) throws Exception {
        initGame(setup);
        initUI(setup);
    }

    private void initGame(PlayerSetup.SetupResult setup) throws Exception {

        Player human = new HumanPlayer(setup.playerName);
        Player ai = new ComputerPlayer("Computer");


        if (setup.playerFirst) {
            engine = new Engine(human, ai, setup.boardRows, setup.boardCols, setup.connectN, setup.difficultyLevel);
        } else {
            engine = new Engine(ai, human, setup.boardRows, setup.boardCols, setup.connectN, setup.difficultyLevel);
        }
    }

    private void initUI(PlayerSetup.SetupResult setup) {
        setTitle("Connect "+setup.connectN);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(engine);

        sidePanel = new SidePanel(
                setup.playerName,
                setup.playerAvatar,
                "AI",
                setup.computerAvatar
        );

        if(!setup.playerFirst)
            sidePanel = new SidePanel(
                    "AI",
                    setup.computerAvatar,
                    setup.playerName,
                    setup.playerAvatar
            );

        add(gamePanel, BorderLayout.CENTER);
        add(sidePanel, BorderLayout.EAST);

        sidePanel.p1Panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3)); // highlight player 1 avatar
        if(!setup.playerFirst) {
            engine.aiMove(); // if AI goes first, make the first move immediately
            sidePanel.p1Panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
            sidePanel.p2Panel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2)); // un-highlight player 2 avatar
            gamePanel.repaint();
        }


        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PlayerSetup.SetupResult setup = PlayerSetup.showSetupDialog();
            try {
                new ConnectNUI(setup);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}