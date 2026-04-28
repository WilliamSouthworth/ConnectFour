/**
 * Abstract base class representing a game player.
 * 
 * @author Archange DESTINE
 * @author YOUR_NAME_HERE
 */
public abstract class Player implements Displayable {

    private String displayName;
    private Avatar avatar;

    // Constructor that initializes a player with a name and avatar.
    public Player(String displayName, Avatar avatar) throws IllegalArgumentException {

        if (displayName == null ||
                displayName.length() < 2 ||
                displayName.length() > 15) {

            throw new IllegalArgumentException(
                    "Display name must be between 2 and 15 characters.");
        }

        // Checks for prohibited words
        if (Utility.containsBadWord(displayName, "bad_words.txt")) {
            throw new IllegalArgumentException(
                    "Display name contains prohibited words.");
        }

        this.displayName = displayName;

        if (avatar == null) {
            this.avatar = Avatar.AVATAR_A;
        } else {
            this.avatar = avatar;
        }
    }

    public Player(String displayName) throws IllegalArgumentException {
        this(displayName, null);
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}