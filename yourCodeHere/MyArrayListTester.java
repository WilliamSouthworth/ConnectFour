import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field; //Needed to check private fields.

/**
 * Unit Test for MyArrayList using JUnit Framework (v.4).
 */

public class MyArrayListTester {
    
    @Test(timeout = 1000)
    public void constructor_test01() {
        MyArrayList<String> list = new MyArrayList<>();
        Object[] data = null;
        //Why Object? Please review the slides on Generics/Type Erasure.

        //Using reflection to access and check private field is fun... and useful.
        Field privateField = null;
        try {
            privateField = MyArrayList.class.getDeclaredField("data");
            privateField.setAccessible(true);
            data = (Object[]) privateField.get(list);
        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        if(data == null)
            fail("The data field should not be null fater initializing MyArrayList.");
        
        if(data.length != 500)
            fail("The length of the data field should be 500 (always, for this project)");
    }

    @Test(timeout = 1000)
    public void constructor_test02() {
        //cheching if MyArrayList is really Generic!

        //Using is for Strings
        MyArrayList<String> list1 = new MyArrayList<>();

        //Using if for Cars
        class Car{}
        MyArrayList<Car> list2 = new MyArrayList<>();

        //Using if for ProgrammingAssignments
        class ProgrammingAssignment{}
        MyArrayList<ProgrammingAssignment> list3 = new MyArrayList<>();
        
    }

    @Test(timeout = 1000)
    public void add1_test01() {

        MyArrayList<String> list = new MyArrayList<>();
        list.add(0, "Hi");
        list.add(1, "Bonjour");
        list.add(1, "Hola");
        list.add(1, "Bonjou");

        //using reflection again to check the content of the private field data.
//Using reflection to access and check private field is fun... and useful.
        Field privateField = null;
        Object[] data = null;
        try {
            privateField = MyArrayList.class.getDeclaredField("data");
            privateField.setAccessible(true);
            data = (Object[]) privateField.get(list);
        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        
        if (!data[0].equals("Hi")
            || !data[1].equals("Bonjou")
            || !data[2].equals("Hola")
            || !data[3].equals("Bonjour")) {

                fail("add(index, element) did not insert the element at the correct index or failed to shift the data correctlty.");
            
        }
    }

    @Test(timeout = 1000)
    public void add2_test01() {

        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hi");
        list.add("Bonjour");
        list.add("Hola");
        list.add("Bonjou");

        //using reflection again to check the content of the private field data.
        //Using reflection to access and check private field is fun... and useful.
        Field privateField = null;
        Object[] data = null;
        try {
            privateField = MyArrayList.class.getDeclaredField("data");
            privateField.setAccessible(true);
            data = (Object[]) privateField.get(list);
        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        
        if (!data[0].equals("Hi")
            || !data[1].equals("Bonjour")
            || !data[2].equals("Hola")
            || !data[3].equals("Bonjou")) {

                fail("add(element) did not insert the element at the correct index or failed to shift the data correctlty.");
            
        }

    }

    @Test(timeout = 1000, expected=IllegalStateException.class)
    public void add_test03() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for(int i=0; i<501; i++) {
            list.add(i);
        }
    }

    @Test (timeout = 1000)
    public void get_test01() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hi");
        list.add("Bonjour");
        list.add("Hola");
        list.add("Bonjou");

        Assert.assertEquals("Hi", list.get(0));
        Assert.assertEquals("Bonjour", list.get(1));
        Assert.assertEquals("Hola", list.get(2));
    }

    @Test (timeout = 1000)
    public void remove_test01() {

        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hi");
        list.add("Bonjour");
        list.add("Hola");
        list.add("Bonjou");

        list.remove(1);

        //using reflection again to check the content of the private field data.
        //Using reflection to access and check private field is fun... and useful.
        Field privateField = null;
        Object[] data = null;
        try {
            privateField = MyArrayList.class.getDeclaredField("data");
            privateField.setAccessible(true);
            data = (Object[]) privateField.get(list);
        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        
        if (!data[0].equals("Hi")
            || !data[1].equals("Hola")
            || !data[2].equals("Bonjou")
        ) {

                fail("remove() did not remove the element correctlty.");
        }

        //Better, we could test the size field content directly using reflection as above. (since the size method will be tested separately)
        Assert.assertEquals(3, list.size());

    }

    @Test(timeout = 1000)
    public void size_test01() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("Hi");
        Assert.assertEquals(1, list.size());

        list.add("Bonjour");
        Assert.assertEquals(2, list.size());

        list.add("Hola");
        Assert.assertEquals(3, list.size());

        list.add("Bonjou");
        Assert.assertEquals(4, list.size());

    }

    @Test(timeout = 1000)
    public void isEmpty_test01() {

        MyArrayList<String> list = new MyArrayList<>();

        Assert.assertTrue(list.isEmpty());
    }

    public void contains_test01() {

        MyArrayList<String> list = new MyArrayList<>();

        list.add("Toto");
        list.add("Foo");
        Assert.assertTrue(list.contains("Foo"));
        Assert.assertTrue(list.contains("Foo"));
        Assert.assertFalse(list.contains("Bar"));

    }

    @Test(timeout = 1000)
    public void iterator_test01() {

        class Flower {}
        Flower[] arr = new Flower[500];
        MyArrayList<Flower> list = new MyArrayList<>();

        
        for(int i = 0; i<500; i++) {
            arr[i] = new Flower();
            list.add(arr[i]);
        }

        int i = 0;
        for(Flower f : list) {
            Assert.assertSame(arr[i++], f);
        }
    }

}
