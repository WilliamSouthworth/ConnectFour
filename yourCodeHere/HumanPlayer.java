/**
 * Subclass of Player representing a human player.
 * 
 * @author Archange DESTINE
 * @author YOUR_NAME_HERE
 */
public class HumanPlayer extends Player {

    // Constructor that calls parent constructor
    public HumanPlayer(String displayName, Avatar avatar) throws IllegalArgumentException {
        super(displayName, avatar);
    }

    public HumanPlayer(String displayName) throws IllegalArgumentException {
        this(displayName, null);
    }

}