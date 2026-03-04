package lego.parser;

import java.util.Scanner;

public class InputParser {

    private String input;
    private String command;
    private int taskNum;
    private Scanner scanner;

    public InputParser() {
        this.scanner = new Scanner(System.in);
    }

    public void getNextLine() {
        this.input = this.scanner.nextLine();
        this.processInput();
    }

    public void processInput() {
        String[] splitCommand = this.input.split(" ");
        this.command = splitCommand[0];
        if (splitCommand.length > 1) {
            try {
                this.taskNum = Integer.parseInt(splitCommand[1]);
            } catch (NumberFormatException e) {
                // Do nothing, as taskNum will not be used for non-mark/unmark commands
            }
        }
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public String getInput() {
        return this.input;
    }

    public String getCommand() {
        return this.command;
    }

    public int getTaskNum() {
        return this.taskNum;
    }
}
