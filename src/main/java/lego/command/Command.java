package lego.command;

import java.io.IOException;

import lego.ui.Ui;
import lego.exception.LegoException;
import lego.task.Tasklist;
import lego.database.DatabaseHandler;

/**
 * Represents a command that can be executed on the task list.
 * 
 * Parses and executes user commands such as adding, deleting, and marking
 * tasks.
 * Manages the running state of the application.
 */
public class Command {

    /**
     * Constructs a Command with the specified instruction, input, and task number.
     * 
     * @param instruction the command type (e.g., "todo", "deadline", "delete")
     * @param input       the additional input for the command
     * @param taskNum     the task number to operate on
     */
    public Command(String instruction, String input, int taskNum) {
        this.command = instruction;
        this.input = input;
        this.taskNum = taskNum;
    }

    private String command;
    private int taskNum;
    private String input;
    private boolean isRunning = true;

    public String getCommand() {
        return this.command;
    }

    public void execute(Tasklist tasks, Ui ui, DatabaseHandler dbHandler) {
        switch (this.command) {
            case "bye":
                this.isRunning = false;
                ui.showClosingText();
                break;
            case "list":
                tasks.listTasks();
                break;
            case "mark":
                try {
                    tasks.markTask(this.taskNum);
                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidNumberError();
                }
                break;
            case "unmark":
                try {
                    tasks.unmarkTask(this.taskNum);
                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidNumberError();
                }
                break;
            case "todo":
                try {
                    tasks.addTodo(this.input);
                } catch (LegoException e) {
                    ui.showEmptyDescriptionError("todo");
                }
                break;
            case "deadline":
                try {
                    tasks.addNewDeadline(this.input);
                } catch (IndexOutOfBoundsException e) {
                    ui.showDeadlineFormatError();
                }
                break;
            case "event":
                try {
                    tasks.addNewEvent(this.input);
                } catch (IndexOutOfBoundsException e) {
                    ui.showEventFormatError();
                }
                break;
            case "delete":
                try {
                    tasks.deleteEvent(this.taskNum);
                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidNumberError();
                }
                break;
            case "save":
                try {
                    dbHandler.saveToFile(tasks.getTaskList());
                } catch (IOException e) {
                    ui.showFileSaveError();
                }
                break;
            default:
                ui.showInvalidCommandError();
                break;
        }
    }

    /**
     * Determines whether the application should continue running.
     * 
     * @return true if the application should continue, false if it should terminate
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}
