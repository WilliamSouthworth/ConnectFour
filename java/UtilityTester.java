import org.junit.Test;
import org.junit.Assert;

public class UtilityTester {

    @Test(timeout = 1000)
    public void loadBadWords_test01() throws Exception {
        MyArrayList<String> badWords = Utility.loadBadWords("bad_words.txt");
        Assert.assertTrue(badWords.contains("yucky"));
        Assert.assertTrue(badWords.contains("icky"));
        Assert.assertTrue(badWords.contains("gross"));
        Assert.assertTrue(badWords.contains("stinky"));
        Assert.assertTrue(badWords.contains("silly"));
        Assert.assertTrue(badWords.contains("dummy"));
        Assert.assertTrue(badWords.contains("meanie"));
        Assert.assertTrue(badWords.contains("loser"));
    }

    @Test(timeout = 1000)
    public void containsBadWord_test01() throws Exception {
        
        Assert.assertTrue(Utility.containsBadWord("yucky", "bad_words.txt"));
        Assert.assertTrue(Utility.containsBadWord("yucky food", "bad_words.txt"));
        Assert.assertTrue(Utility.containsBadWord("yuckyfood", "bad_words.txt"));
        Assert.assertTrue(Utility.containsBadWord("foodisyucky", "bad_words.txt"));
        Assert.assertTrue(Utility.containsBadWord("foodisyucky", "bad_words.txt"));

        Assert.assertFalse(Utility.containsBadWord("validname", "bad_words.txt"));

    }

    @Test(timeout = 1000)
    public void isEven_test01() throws Exception {
        
        Assert.assertTrue(Utility.isEven(0));
        Assert.assertTrue(Utility.isEven(2));
        Assert.assertTrue(Utility.isEven(4));
        Assert.assertTrue(Utility.isEven(6));
        Assert.assertTrue(Utility.isEven(8));
        Assert.assertTrue(Utility.isEven(1248));

        Assert.assertFalse(Utility.isEven(1));
        Assert.assertFalse(Utility.isEven(3));
        Assert.assertFalse(Utility.isEven(5));
        Assert.assertFalse(Utility.isEven(7));
        Assert.assertFalse(Utility.isEven(9));
        Assert.assertFalse(Utility.isEven(1249));
    }

    @Test(timeout = 1000)
    public void isOdd_test01() throws Exception {
        
        Assert.assertFalse(Utility.isOdd(0));
        Assert.assertFalse(Utility.isOdd(2));
        Assert.assertFalse(Utility.isOdd(4));
        Assert.assertFalse(Utility.isOdd(6));
        Assert.assertFalse(Utility.isOdd(8));
        Assert.assertFalse(Utility.isOdd(1248));

        Assert.assertTrue(Utility.isOdd(1));
        Assert.assertTrue(Utility.isOdd(3));
        Assert.assertTrue(Utility.isOdd(5));
        Assert.assertTrue(Utility.isOdd(7));
        Assert.assertTrue(Utility.isOdd(9));
        Assert.assertTrue(Utility.isOdd(1249));
    }

    //public static int countInColumn(int[][] board, int col, int piece)
    @Test(timeout=1000)
    public void countInColumn_test01() {
        int[][] board = new int[][] { {1,0,2}, {0,1,2}, {1,0,2} };
        Assert.assertEquals(2, Utility.countInColumn(board,0,1));
        Assert.assertEquals(1, Utility.countInColumn(board,1,1));
        Assert.assertEquals(0, Utility.countInColumn(board,2,1));
        Assert.assertEquals(0, Utility.countInColumn(board,0,2));
        Assert.assertEquals(0, Utility.countInColumn(board,1,2));
        Assert.assertEquals(3, Utility.countInColumn(board,2,2));
    }

}