import javax.swing.*;

/**
 * Contains mainly statics methods used by the UI.
 * Those are used to collect setting information from the user the the program is executed.
 * @author Archange DESTINE
 */
public class PlayerSetup {

    public static class SetupResult {
        String playerName;
        Avatar playerAvatar;
        Avatar computerAvatar;
        boolean playerFirst;
        DifficultyLevel difficultyLevel;
        int connectN;
        int boardRows; //must be greater than or equal to connectN
        int boardCols; //must be greater than or equal to connectN
    }

    public static SetupResult showSetupDialog() {
        SetupResult result = new SetupResult();

        // 1. Ask player name
        result.playerName = JOptionPane.showInputDialog(
                null,
                "Enter your name:",
                "Player Setup",
                JOptionPane.QUESTION_MESSAGE
        );

        if (result.playerName == null || result.playerName.isEmpty()) {
            result.playerName = "Player";
        }

        // 2. Choose avatar from a list of names.
        String[] avatarNames = {"Avatar A", "Avatar B", "Avatar C", "Avatar D", "Avatar E", "Avatar F", "Avatar G", "Avatar H", "Avatar I"};
        String selectedAvatar = (String) JOptionPane.showInputDialog(
                null,
                "Choose your avatar:",
                "Player Setup",
                JOptionPane.QUESTION_MESSAGE,
                null,
                avatarNames,
                avatarNames[0]
        );

        if (selectedAvatar != null) {
            result.playerAvatar = Avatar.valueOf(selectedAvatar.replace(" ", "_").toUpperCase());
        } else {
            //pick a random avatar if none selected
            result.playerAvatar = Avatar.values()[(int) (Math.random() * Avatar.values().length)];
        }

        // 3. AI avatar (random but must be different from player avatar)
        result.computerAvatar = Avatar.values()[(int) (Math.random() * Avatar.values().length)];
        while (result.computerAvatar == result.playerAvatar) {
            result.computerAvatar = Avatar.values()[(int) (Math.random() * Avatar.values().length)];
        }

        // 4. Who plays first?
        int first = JOptionPane.showConfirmDialog(
                null,
                "Do you want to play first?",
                "Turn Selection",
                JOptionPane.YES_NO_OPTION
        );
        result.playerFirst = (first == JOptionPane.YES_OPTION);

        //How many in a row to win?
        String connectNStr = JOptionPane.showInputDialog(
                null,
                "How many in a row to win? (Default is 4)",
                "Connect N",
                JOptionPane.QUESTION_MESSAGE
        );
        try {
            int connectN = Integer.parseInt(connectNStr);
            if (connectN >= 3 && connectN <= 6) {
                result.connectN = connectN;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Using default of 4.");
                result.connectN = 4;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Using default of 4.");
            result.connectN = 4;
        }

        // 5. Difficulty level
        DifficultyLevel[] levels = DifficultyLevel.values();
        DifficultyLevel selectedLevel = (DifficultyLevel) JOptionPane.showInputDialog(
                null,
                "Select AI difficulty level:",
                "Difficulty Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                levels,
                levels[0]
        );
        result.difficultyLevel = (selectedLevel != null) ? selectedLevel : DifficultyLevel.EASY;

        String rowsStr = JOptionPane.showInputDialog(
                null,
                "Enter number of rows (Default is 8):",
                "Board Size",
                JOptionPane.QUESTION_MESSAGE
        );

        try {
            int rows = Integer.parseInt(rowsStr);
            if (rows >= result.connectN && rows <= 10) {
                result.boardRows = rows;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Using default of 8.");
                result.boardRows = 8;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Using default of 8.");
            result.boardRows = 8;
        }

        // 6. Board columns
        String colsStr = JOptionPane.showInputDialog(
                null,
                "Enter number of columns (Default is 10):",
                "Board Size",
                JOptionPane.QUESTION_MESSAGE
        );
        try {
            int cols = Integer.parseInt(colsStr);
            if (cols >= result.connectN && cols <= 12) {
                result.boardCols = cols;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input. Using default of 10.");
                result.boardCols = 10;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Using default of 10.");
            result.boardCols = 10;
        }

        return result;
    }
}