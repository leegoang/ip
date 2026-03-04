package lego;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

import lego.database.DatabaseHandler;
import lego.exception.LegoException;
import lego.parser.InputParser;
import lego.task.Tasklist;
import lego.ui.Ui;

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
        Scanner in = new Scanner(System.in);
        Boolean isRunning = true;

        this.ui.showOpeningText();

        while (isRunning) {
            InputParser.getNextLine(in);
            String command = InputParser.getCommand();
            switch (command) {
                case "bye":
                    isRunning = false;
                    this.ui.showClosingText();
                    break;
                case "list":
                    this.tasks.listTasks();
                    break;
                case "mark":
                    try {
                        this.tasks.markTask(InputParser.getTaskNum());
                    } catch (IndexOutOfBoundsException e) {
                        this.ui.showInvalidNumberError();
                    }
                    break;
                case "unmark":
                    try {
                        this.tasks.unmarkTask(InputParser.getTaskNum());
                    } catch (IndexOutOfBoundsException e) {
                        this.ui.showInvalidNumberError();
                    }
                    break;
                case "todo":
                    try {
                        this.tasks.addTodo(InputParser.getInput());
                    } catch (LegoException e) {
                        this.ui.showEmptyDescriptionError("todo");
                    }
                    break;
                case "deadline":
                    try {
                        this.tasks.addNewDeadline(InputParser.getInput());
                    } catch (IndexOutOfBoundsException e) {
                        this.ui.showDeadlineFormatError();
                    }
                    break;
                case "event":
                    try {
                        this.tasks.addNewEvent(InputParser.getInput());
                    } catch (IndexOutOfBoundsException e) {
                        this.ui.showEventFormatError();
                    }
                    break;
                case "delete":
                    try {
                        this.tasks.deleteEvent(InputParser.getTaskNum());
                    } catch (IndexOutOfBoundsException e) {
                        this.ui.showInvalidNumberError();
                    }
                    break;
                case "save":
                    try {
                        this.dbHandler.saveFileContents(this.tasks.getTaskList());
                    } catch (IOException e) {
                        this.ui.showFileSaveError();
                    }
                    break;
                default:
                    this.ui.showInvalidCommandError();
                    break;
            }
        }
        in.close();
    }

    public static void main(String[] args) {
        Lego lego = new Lego("../data/lego.txt");
        lego.run();
    }

}
