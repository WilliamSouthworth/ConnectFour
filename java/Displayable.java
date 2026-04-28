/**
 * A functional interface, i.e. an interface with a single method declaration.
 * Any class representing an object with a getDisplayName() method must implement this interface. In this project, the Player class implements Displayable.
 * @author Archange DESTINE
 */
public interface Displayable {
    String getDisplayName();
}