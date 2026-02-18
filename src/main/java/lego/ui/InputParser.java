package lego.ui;

import java.util.Scanner;

public class InputParser {

    private static String input;
    private static String command;
    private static int taskNum;

    public static void getNextLine(Scanner scanner) {
        input = scanner.nextLine();
        processInput();
    }

    public static void processInput() {
        String[] splitCommand = input.split(" ");
        command = splitCommand[0];
        if (splitCommand.length > 1) {
            try {
                taskNum = Integer.parseInt(splitCommand[1]);
            } catch (NumberFormatException e) {
                // Do nothing, as taskNum will not be used for non-mark/unmark commands
            }
        }
    }

    public static String getInput() {
        return input;
    }

    public static String getCommand() {
        return command;
    }

    public static int getTaskNum() {
        return taskNum;
    }
}
