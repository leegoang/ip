package lego;

import java.io.FileNotFoundException;

import lego.database.DatabaseHandler;
import lego.parser.InputParser;
import lego.task.Tasklist;
import lego.ui.Ui;
import lego.command.Command;

/**
 * Main entry point for the Lego task management application.
 * 
 * Initializes the application components (database, UI, task list) and runs the
 * main event loop
 * to process user commands until the application is terminated.
 */
public class Lego {

    private Tasklist tasks;
    private Ui ui;
    private DatabaseHandler dbHandler;

    /**
     * Constructs a Lego instance and initializes the application.
     * 
     * Initializes the database handler, task list, and UI. Attempts to load
     * existing tasks
     * from the specified file. If the file is not found, displays an error message.
     * 
     * @param filePath the path to the data file for persisting tasks
     */
    public Lego(String filePath) {
        this.dbHandler = new DatabaseHandler(filePath);
        this.tasks = new Tasklist();
        this.ui = new Ui();

        try {
            this.tasks.setTaskList(this.dbHandler.loadFileContents());
        } catch (FileNotFoundException e) {
            this.ui.showFileMissingError();
        }
    }

    /**
     * Runs the main event loop of the application.
     * 
     * Continuously reads user input, parses commands, executes them, and updates
     * the task list
     * and database until the user initiates shutdown.
     */
    public void run() {
        InputParser inputParser = new InputParser();
        Boolean isRunning = true;

        this.ui.showOpeningText();

        while (isRunning) {
            inputParser.getNextLine();
            String instruction = inputParser.getCommand();
            int taskNum = inputParser.getTaskNum();
            String input = inputParser.getInput();
            Command cmd = new Command(instruction, input, taskNum);
            cmd.execute(this.tasks, this.ui, this.dbHandler);
            isRunning = cmd.isRunning();
        }
        inputParser.getScanner().close();
    }

    /**
     * Main method that serves as the entry point for the application.
     * 
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        Lego lego = new Lego("../data/lego.txt");
        lego.run();
    }

}
