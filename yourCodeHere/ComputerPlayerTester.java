import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field; //Needed to check private fields.

public class ComputerPlayerTester {
    
    @Test(timeout=1000, expected = IllegalArgumentException.class)
    public void constructor_test01() {
        Avatar av = Avatar.AVATAR_A;
        new ComputerPlayer("P", av);
        new ComputerPlayer("AnyName1234512345", av);
    }

    @Test(timeout=1000, expected = IllegalArgumentException.class)
    public void constructor_test02() {
        Avatar av = Avatar.AVATAR_A;
        new ComputerPlayer("yucky", av);
    }

    @Test(timeout=1000, expected = IllegalArgumentException.class)
    public void constructor_test03() {
        Avatar av = Avatar.AVATAR_A;
        new ComputerPlayer("YUckY", av);
    }

    @Test(timeout=1000, expected = IllegalArgumentException.class)
    public void constructor_test04() {
        Avatar av = Avatar.AVATAR_A;
        new ComputerPlayer("AnyName1234512345", av);
    }

    @Test(timeout=1000)
    public void constructor_test05() {
        Avatar avUsed = Avatar.AVATAR_A;
        ComputerPlayer hp = new ComputerPlayer("ValidName", avUsed);
        //checking if the private field of the parent class Player have correct values.

        //Field displayName
        String displayNameFound = null;
        Avatar avFound = null;
        //Why Object? Please review the slides on Generics/Type Erasure.

        //Using reflection to access and check private field is fun... and useful.
        Field privateField = null;
        try {
            privateField = Player.class.getDeclaredField("displayName");
            privateField.setAccessible(true);
            displayNameFound = (String) privateField.get(hp);
        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertEquals("ValidName", displayNameFound);


        privateField = null;
        try {
            privateField = Player.class.getDeclaredField("avatar");
            privateField.setAccessible(true);
            avFound = (Avatar) privateField.get(hp);
        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertSame(avUsed, avFound);

    }

    @Test(timeout=1000)
    public void constructor_test06() throws IllegalArgumentException {
        Avatar avUsed = Avatar.AVATAR_A;
        ComputerPlayer hp = new ComputerPlayer("ValidName", null);
        //checking if the private field of the parent class Player have correct values.

        //Field displayName
        Avatar avFound = null;
        //Why Object? Please review the slides on Generics/Type Erasure.

        //Using reflection to access and check private field is fun... and useful.
        Field privateField = null;
        
        try {
            privateField = Player.class.getDeclaredField("avatar");
            privateField.setAccessible(true);
            avFound = (Avatar) privateField.get(hp);
        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertSame(avUsed, avFound);

    }

}
