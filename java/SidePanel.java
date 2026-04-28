import javax.swing.*;
import java.awt.*;

/**
 * Represents the side panel of the game UI where the player's info are displayed.
 * @author Archange DESTINE
 */
public class SidePanel extends JPanel {

    JPanel p1Panel;
    JPanel p2Panel;

    public SidePanel(String p1Name, Avatar p1Avatar, String p2Name, Avatar p2Avatar) {
        String p1AvatarPath = "resources/" + p1Avatar.name() + ".png";
        String p2AvatarPath = "resources/" + p2Avatar.name() + ".png";

        setPreferredSize(new Dimension(200, 600));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);

        add(Box.createVerticalStrut(20));
        p1Panel = createPlayer(p1Name, p1AvatarPath);
        add(p1Panel);
        add(Box.createVerticalStrut(40));
        p2Panel = createPlayer(p2Name, p2AvatarPath);
        add(p2Panel);
    }

    private JPanel createPlayer(String name, String avatarPath) {
        //add border around the avatar to indicate whose turn it is. We can do this by passing the player names and avatars to the side panel, and then updating the side panel whenever the turn changes.

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);

        ImageIcon icon = new ImageIcon(avatarPath);
        JLabel avatar = new JLabel(icon);
        avatar.setHorizontalAlignment(JLabel.CENTER);
        avatar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));  

        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setForeground(Color.WHITE);

        panel.add(avatar, BorderLayout.CENTER);
        panel.add(label, BorderLayout.SOUTH);

        return panel;
    }
}