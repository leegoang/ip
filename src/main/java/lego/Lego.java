package lego;

import java.io.FileNotFoundException;

import lego.database.DatabaseHandler;
import lego.parser.InputParser;
import lego.task.Tasklist;
import lego.ui.Ui;
import lego.command.Command;

public class Lego {

    private Tasklist tasks;
    private Ui ui;
    private DatabaseHandler dbHandler;

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

    public static void main(String[] args) {
        Lego lego = new Lego("../data/lego.txt");
        lego.run();
    }

}
