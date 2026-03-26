package lego.parser;

import java.util.Scanner;

/**
 * Parses user input into command components.
 * 
 * Reads lines from the user and extracts:
 * - the command keyword
 * - the task number (if applicable)
 * - the remaining input string
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
     * Reads the next line of input from the user and processes it.
     *
     * Extracts command, task number, and remaining input.
     */
    public void getNextLine() {
        this.userInput = this.scanner.nextLine();
        this.processInput();
    }

    /**
     * Processes the raw user input into structured components.
     *
     * Extracts:
     * - command (first word)
     * - parsedInput (remaining text after command)
     * - taskNum (if second word is a valid number)
     *
     * Task number is converted to zero-based indexing.
     */
    public void processInput() {
        String[] splitCommand = this.userInput.split(" ");
        this.command = splitCommand[0].toLowerCase();

        this.parsedInput = this.userInput
                .trim()
                .substring(splitCommand[0].length())
                .trim();

        this.taskNum = -1;

        if (splitCommand.length > 1) {
            try {
                this.taskNum = Integer.parseInt(splitCommand[1]) - 1;
            } catch (NumberFormatException e) {
                // ignore
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
     * Retrieves the raw user input from the last input line.
     *
     * @return the full input string
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
     * Returns -1 if no valid number was provided.
     *
     * @return the zero-based task index
     */
    public int getTaskNum() {
        return this.taskNum;
    }

    /**
     * Retrieves the parsed input excluding the command keyword.
     *
     * @return the remaining input string
     */
    public String getParsedInput() {
        return this.parsedInput;
    }
}
