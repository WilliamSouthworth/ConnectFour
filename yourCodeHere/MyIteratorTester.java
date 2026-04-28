import org.junit.Test;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * Unit Test for MyIterator using JUnit Framework (v.4).
 */

public class MyIteratorTester {

    //You want to have a timeout in your Unit Tests.
    @Test(timeout = 1000)
    public void hasNext_test01() {
        MyArrayList<String> list = new MyArrayList<>();
        MyIterator<String> it = new MyIterator<>(list);
        
        //We expect hasNext() to return false
        boolean output = it.hasNext();
        //We use assertFalse(message_if_failure, value_expected_to_be_false)
        assertFalse("hasNext should return false for a newly created (empty) list.", output);
    }

    @Test(timeout = 1000)
    public void hasNext_test02() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("CS 112");
        list.add("CS 211");
        list.add("CS 310");

        MyIterator<String> it = new MyIterator<>(list);
        //Callinf next() 1 time
        it.next();
        //We expect hasNext() to return true
        assertTrue("hasNext should return true after just 1 call to next().", it.hasNext());

        //Callinf next() a second time
        it.next();
        //We expect hasNext() to return true again
        assertTrue("hasNext should return true after just 2 calls to next().", it.hasNext());

        //Callinf next() a third time  
        it.next();
        //We use assertFalse(message_if_failure, value_expected_to_be_false)
        assertFalse("hasNext should return false after 3 calls to next().", it.hasNext());
    }

    @Test(timeout = 1000)
    public void next_test01() {
        MyArrayList<String> list = new MyArrayList<>();
        String item1 = "CS 112";
        String item2 = "CS 211";
        String item3 = "CS 310";

        list.add(item1);
        list.add(item2);
        list.add(item3);

        MyIterator<String> it = new MyIterator<>(list);
        //Callinf next() 1 time
        assertTrue("the first call to next() did not provide the correct item", it.next() == item1);

        //Callinf next() a second time
        assertTrue("the second call to next() did not provide the correct item", it.next() == item2);

        //Callinf next() a third time  
        assertTrue("the third call to next() did not provide the correct item", it.next() == item3);
    }

    @Test(timeout = 1000, expected = NoSuchElementException.class)
    public void next_test02() {
        MyArrayList<String> list = new MyArrayList<>();
        String item1 = "CS 112";
        String item2 = "CS 211";

        list.add(item1);
        list.add(item2);

        //Calling next() 3 times and expect to see a NoSuchElementException exception. Have a look at the @Test annotation
        MyIterator<String> it = new MyIterator<>(list);
        it.next();
        it.next();
        it.next();

    }


    
}
