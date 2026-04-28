/**
 * Subclass of Player representing the AI/Computer player.
 * 
 * @author Archange DESTINE
 * @author YOUR_NAME_HERE
 */
public class ComputerPlayer extends Player {

    // Constructor that calls parent constructor
    public ComputerPlayer(String displayName, Avatar avatar)
            throws IllegalArgumentException {

        super(displayName, avatar);
    }

    public ComputerPlayer(String displayName)
            throws IllegalArgumentException {

        super(displayName, null);
    }

}