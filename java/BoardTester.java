import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field; //Needed to check private fields.

public class BoardTester {

    @Test(timeout = 1000)
    public void constructor1_test01() {
        //Board(int rows, int cols, int connectN)
        Board board = new Board(5, 4, 3);
        //Using reflection to test the value of every field


        //Using reflection to access and check private field is fun... and useful.
        int valueFound1 = 0;
        Field privateField1 = null;

        int valueFound2 = 0;
        Field privateField2 = null;

        int valueFound3 = 0;
        Field privateField3 = null;


        try {
            privateField1 = Board.class.getDeclaredField("rows");
            privateField2 = Board.class.getDeclaredField("cols");
            privateField3 = Board.class.getDeclaredField("connectN");

            privateField1.setAccessible(true);
            privateField2.setAccessible(true);
            privateField3.setAccessible(true);

            valueFound1 = (int) privateField1.get(board);
            valueFound2 = (int) privateField2.get(board);
            valueFound3 = (int) privateField3.get(board);

        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertEquals(5, valueFound1);
        Assert.assertEquals(4, valueFound2);
        Assert.assertEquals(3, valueFound3);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void constructor1_test02() {
        new Board(2, 4, 3);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void constructor1_test03() {
        new Board(3, 2, 3);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void constructor1_test04() {
        new Board(4, 5, 2);
    }

    @Test(timeout = 1000)
    public void constructor2_test01() {
        //Board(int rows, int cols)
        Board board = new Board(5, 4);
        //Using reflection to test the value of every field


        //Using reflection to access and check private field is fun... and useful.
        int valueFound1 = 0;
        Field privateField1 = null;

        int valueFound2 = 0;
        Field privateField2 = null;

        int valueFound3 = 0;
        Field privateField3 = null;


        try {
            privateField1 = Board.class.getDeclaredField("rows");
            privateField2 = Board.class.getDeclaredField("cols");
            privateField3 = Board.class.getDeclaredField("connectN");

            privateField1.setAccessible(true);
            privateField2.setAccessible(true);
            privateField3.setAccessible(true);

            valueFound1 = (int) privateField1.get(board);
            valueFound2 = (int) privateField2.get(board);
            valueFound3 = (int) privateField3.get(board);

        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertEquals(5, valueFound1);
        Assert.assertEquals(4, valueFound2);
        Assert.assertEquals(4, valueFound3);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void constructor2_test02() {
        new Board(2, 4);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void constructor2_test03() {
        new Board(4, 3);
    }

    @Test(timeout = 1000)
    public void constructor3_test01() {
        //Board(int rows, int cols)
        Board board = new Board();
        //Using reflection to test the value of every field


        //Using reflection to access and check private field is fun... and useful.
        int valueFound1 = 0;
        Field privateField1 = null;

        int valueFound2 = 0;
        Field privateField2 = null;

        int valueFound3 = 0;
        Field privateField3 = null;


        try {
            privateField1 = Board.class.getDeclaredField("rows");
            privateField2 = Board.class.getDeclaredField("cols");
            privateField3 = Board.class.getDeclaredField("connectN");

            privateField1.setAccessible(true);
            privateField2.setAccessible(true);
            privateField3.setAccessible(true);

            valueFound1 = (int) privateField1.get(board);
            valueFound2 = (int) privateField2.get(board);
            valueFound3 = (int) privateField3.get(board);

        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertEquals(6, valueFound1);
        Assert.assertEquals(7, valueFound2);
        Assert.assertEquals(4, valueFound3);
    }


    @Test(timeout = 1000)
    public void setRows_test01() {
        Board board = new Board(5, 4, 3);
        board.setRows(8);
        //Using reflection to test the value of every field


        //Using reflection to access and check private field is fun... and useful.
        int valueFound1 = 0;
        Field privateField1 = null;

        int valueFound2 = 0;
        Field privateField2 = null;

        int valueFound3 = 0;
        Field privateField3 = null;


        try {
            privateField1 = Board.class.getDeclaredField("rows");
            privateField2 = Board.class.getDeclaredField("cols");
            privateField3 = Board.class.getDeclaredField("connectN");

            privateField1.setAccessible(true);
            privateField2.setAccessible(true);
            privateField3.setAccessible(true);

            valueFound1 = (int) privateField1.get(board);
            valueFound2 = (int) privateField2.get(board);
            valueFound3 = (int) privateField3.get(board);

        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertEquals(8, valueFound1);
        Assert.assertEquals(4, valueFound2);
        Assert.assertEquals(3, valueFound3);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void setRows_test02() {
        Board board = new Board(6, 7, 5);
        board.setRows(4);
    }

    @Test(timeout = 1000)
    public void setCols_test01() {
        Board board = new Board(5, 4, 3);
        board.setCols(8);
        //Using reflection to test the value of every field


        //Using reflection to access and check private field is fun... and useful.
        int valueFound1 = 0;
        Field privateField1 = null;

        int valueFound2 = 0;
        Field privateField2 = null;

        int valueFound3 = 0;
        Field privateField3 = null;


        try {
            privateField1 = Board.class.getDeclaredField("rows");
            privateField2 = Board.class.getDeclaredField("cols");
            privateField3 = Board.class.getDeclaredField("connectN");

            privateField1.setAccessible(true);
            privateField2.setAccessible(true);
            privateField3.setAccessible(true);

            valueFound1 = (int) privateField1.get(board);
            valueFound2 = (int) privateField2.get(board);
            valueFound3 = (int) privateField3.get(board);

        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertEquals(5, valueFound1);
        Assert.assertEquals(8, valueFound2);
        Assert.assertEquals(3, valueFound3);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void setCols_test02() {
        Board board = new Board(8, 7, 6);
        board.setCols(5);
    }

    @Test(timeout = 1000)
    public void setConnectN_test01() {
        Board board = new Board(8, 9, 3);
        board.setConnectN(6);
        //Using reflection to test the value of every field


        //Using reflection to access and check private field is fun... and useful.
        int valueFound1 = 0;
        Field privateField1 = null;

        int valueFound2 = 0;
        Field privateField2 = null;

        int valueFound3 = 0;
        Field privateField3 = null;


        try {
            privateField1 = Board.class.getDeclaredField("rows");
            privateField2 = Board.class.getDeclaredField("cols");
            privateField3 = Board.class.getDeclaredField("connectN");

            privateField1.setAccessible(true);
            privateField2.setAccessible(true);
            privateField3.setAccessible(true);

            valueFound1 = (int) privateField1.get(board);
            valueFound2 = (int) privateField2.get(board);
            valueFound3 = (int) privateField3.get(board);

        } catch (NoSuchFieldException e) {
            fail("Field name might be incorrect.");
        } catch (SecurityException e) {
            fail("Test failed since we could not access the field.");
        } catch (ClassCastException e) {
            fail("The field type is maybe incorrect");
        } catch (IllegalAccessException e) {
            fail("The field type is maybe incorrect");
        }

        Assert.assertEquals(8, valueFound1);
        Assert.assertEquals(9, valueFound2);
        Assert.assertEquals(6, valueFound3);
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void setConnectN_test02() {
        Board board = new Board(8, 7, 6);
        board.setConnectN(8);
    }


    @Test(timeout=1000)
    public void isValidMove_test01() {
        //int rows = 3;
        //int cols = 4;
        int[][] boardState = {{1,0,0,2},{2,0,1,1},{1,2,2,1}};

        Assert.assertTrue(Board.isValidMove(boardState, 1));
        Assert.assertTrue(Board.isValidMove(boardState, 2));

        Assert.assertFalse(Board.isValidMove(boardState, 0));
        Assert.assertFalse(Board.isValidMove(boardState, 3));
    }


    @Test(timeout=1000)
    public void copyAGivenBoardState_test01() {
        int[][] boardState = {{1,0,0,2},{2,0,1,1},{1,2,2,1}};
        int[][] deepCopy = Board.copyAGivenBoardState(boardState);

        if(boardState == deepCopy)
            fail("This method must return a deep copy of the boardstate provided as argument... not the same reference.");

        if(boardState.length != deepCopy.length)
            fail("The 2 board states must have same number of rows");

        for(int i=0; i < boardState.length; i++) {

            if(boardState[i].length != deepCopy[i].length)
                fail("The 2 boards states must have same dimention");

            for(int j=0; j<boardState[i].length; j++) {
                if(boardState[i][j] != deepCopy[i][j])
                    fail("The 2 board states must have same contents!");
            }

        }
    }


    @Test(timeout=1000) 
    public void dropPieceSimulated_test01() {
        int[][] boardState = {{1,0,0,2},{2,0,1,1},{1,2,2,1}};
        
        int result1 = Board.dropPieceSimulated(boardState, 0, 2);
        Assert.assertEquals(-1, result1);

        int result2 = Board.dropPieceSimulated(boardState, 3, 2);
        Assert.assertEquals(-1, result2);

        int result3 = Board.dropPieceSimulated(boardState, 1, 2);
        Assert.assertEquals(1, result3);

        int result4 = Board.dropPieceSimulated(boardState, 1, 1);
        Assert.assertEquals(0, result4);

        int result5 = Board.dropPieceSimulated(boardState, 2, 2);
        Assert.assertEquals(0, result5);
    }


    @Test(timeout=1000) 
    public void dropPieceSimulated_test02() {
        int[][] boardState = {{1,0,0,2},{2,0,1,1},{1,2,2,1}};
        
        Board.dropPieceSimulated(boardState, 1, 2);
        int[][] boardStatesExpected = {{1,0,0,2},{2,2,1,1},{1,2,2,1}};

        for(int i=0; i < boardState.length; i++) {

            if(boardState[i].length != boardStatesExpected[i].length)
                fail("The 2 boards states must have same dimention");

            for(int j=0; j<boardState[i].length; j++) {
                if(boardState[i][j] != boardStatesExpected[i][j])
                    fail("The 2 board states must have same contents!");
            }

        }


        Board.dropPieceSimulated(boardState, 1, 1);
        int[][] boardStatesExpected2 = {{1,1,0,2},{2,2,1,1},{1,2,2,1}};

        for(int i=0; i < boardState.length; i++) {

            if(boardState[i].length != boardStatesExpected2[i].length)
                fail("The 2 boards states must have same dimention");

            for(int j=0; j<boardState[i].length; j++) {
                if(boardState[i][j] != boardStatesExpected2[i][j])
                    fail("The 2 board states must have same contents!");
            }

        }

    }

    @Test(timeout=1000)
    public void isBoardFullForBoard_test01() {

        int[][] boardState1 = {{1,0,0,2},{2,0,1,1},{1,2,2,1}};
        Assert.assertFalse(Board.isBoardFullForBoard(boardState1));

        int[][] boardState2 = {{1,1,2,2},{2,2,1,1},{1,2,2,1}};
        Assert.assertTrue(Board.isBoardFullForBoard(boardState2));
    }


}
