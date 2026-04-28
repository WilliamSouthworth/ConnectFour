import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Utility {

    public static MyArrayList<String> loadBadWords(String filename) {

        MyArrayList<String> words = new MyArrayList<>();

        try {
            Scanner fileScan = new Scanner(new File(filename));

            while (fileScan.hasNextLine()) {
                String word = fileScan.nextLine().trim();

                if (!word.equals("")) {
                    words.add(word);
                }
            }

            fileScan.close();

        } catch (FileNotFoundException e) {
            return words;
        }

        return words;
    }

    public static boolean containsBadWord(String input, String filename) {

        if (input == null) {
            return false;
        }

        MyArrayList<String> badWords = loadBadWords(filename);
        String lowerInput = input.toLowerCase();

        for (String badWord : badWords) {
            if (lowerInput.contains(badWord.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEven(int number) {

        if (number < 0) {
            number = -number;
        }

        if (number == 0) {
            return true;
        }

        return isOdd(number - 1);
    }

    public static boolean isOdd(int number) {

        if (number < 0) {
            number = -number;
        }

        if (number == 0) {
            return false;
        }

        return isEven(number - 1);
    }

    public static int countInColumn(int[][] board, int col, int piece) {
        return countInColumnHelper(board, col, piece, 0);
    }

    private static int countInColumnHelper(int[][] board, int col, int piece, int row) {

        if (row >= board.length) {
            return 0;
        }

        if (board[row][col] == piece) {
            return 1 + countInColumnHelper(board, col, piece, row + 1);
        }

        return countInColumnHelper(board, col, piece, row + 1);
    }
}