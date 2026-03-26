package lego.parser;

import java.util.Scanner;

/**
 * Parses user input into command components.
 * 
 * Reads lines from the user, extracts the command, task number, and additional
 * input.
 */
public class InputParser {
    private String userInput;
    private String command;
    private int taskNum;
    private Scanner scanner;
    private String parsedInput;

    /**
     * Constructs an InputParser and initializes the scanner.
     */
    public InputParser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     */
    public void getNextLine() {
        this.userInput = this.scanner.nextLine();
        this.processInput();
    }

    public void processInput() {
        String[] splitCommand = this.userInput.split(" ");
        this.command = splitCommand[0].toLowerCase();
        this.parsedInput = this.userInput.trim().substring(splitCommand[0].length()).trim();
        if (splitCommand.length > 1) {
            try {
                this.taskNum = Integer.parseInt(splitCommand[1]) - 1; // Convert to zero-based index
            } catch (NumberFormatException e) {
                // Do nothing, as taskNum will not be used for non-mark/unmark commands
            }
        }
    }

    /**
     * Retrieves the scanner used for reading user input.
     * 
     * @return the Scanner instance
     */
    public Scanner getScanner() {
        return this.scanner;
    }

    /**
     * Retrieves the additional input extracted from the last input line.
     * 
     * @return the input string
     */
    public String getInput() {
        return this.userInput;
    }

    /**
     * Retrieves the command extracted from the last input line.
     * 
     * @return the command string
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Retrieves the task number extracted from the last input line.
     * 
     * @return the task number, or -1 if not applicable
     */
    public int getTaskNum() {
        return this.taskNum;
    }

    public String getParsedInput() {
        return this.parsedInput;
    }
}
