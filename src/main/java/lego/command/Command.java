package lego.command;

import java.io.IOException;

import lego.ui.Ui;
import lego.exception.LegoException;
import lego.task.Tasklist;
import lego.database.DatabaseHandler;

/**
 * Represents a command that can be executed on the task list.
 * 
 * Parses and executes user commands such as adding, deleting, marking,
 * and finding tasks. Delegates all user output to Ui.
 */
public class Command {

    private String command;
    private int taskNum = -1;
    private String input;
    private boolean isRunning = true;

    /**
     * Constructs a Command with the specified instruction, input, and task number.
     * 
     * @param instruction the command keyword (e.g., "todo", "deadline", "delete")
     * @param input       the remaining input string for the command
     * @param taskNum     the task number to operate on (zero-based), or -1 if N/A
     */
    public Command(String instruction, String input, int taskNum) {
        this.command = instruction;
        this.input = input;
        this.taskNum = taskNum;
    }

    /**
     * Returns the command keyword.
     * 
     * @return the command string
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Executes the command on the provided task list.
     * 
     * All output messages are routed through the Ui instance.
     * 
     * @param tasks     the Tasklist to operate on
     * @param ui        the Ui instance for displaying messages
     * @param dbHandler the DatabaseHandler for saving/loading tasks
     */
    public void execute(Tasklist tasks, Ui ui, DatabaseHandler dbHandler) {
        switch (this.command) {
            case "bye":
                this.isRunning = false;
                try {
                    dbHandler.saveToFile(tasks.getTaskList());
                    ui.showFileSavedText();
                } catch (IOException e) {
                    ui.showFileSaveError();
                }
                ui.showClosingText();
                break;

            case "list":
                ui.showMessage(tasks.listTasks());
                break;

            case "mark":
                try {
                    ui.showMessage(tasks.markTask(this.taskNum));
                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidNumberError();
                }
                break;

            case "unmark":
                try {
                    ui.showMessage(tasks.unmarkTask(this.taskNum));
                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidNumberError();
                }
                break;

            case "todo":
                try {
                    ui.showMessage(tasks.addTodo(this.input));
                } catch (LegoException e) {
                    ui.showEmptyDescriptionError("todo");
                }
                break;

            case "deadline":
                try {
                    ui.showMessage(tasks.addNewDeadline(this.input));
                } catch (IndexOutOfBoundsException e) {
                    ui.showDeadlineFormatError();
                } catch (LegoException e) {
                    ui.showEmptyDescriptionError("deadline");
                }
                break;

            case "event":
                try {
                    ui.showMessage(tasks.addNewEvent(this.input));
                } catch (IndexOutOfBoundsException e) {
                    ui.showEventFormatError();
                } catch (LegoException e) {
                    ui.showEmptyDescriptionError("event");
                }
                break;

            case "delete":
                try {
                    ui.showMessage(tasks.deleteTask(this.taskNum));
                } catch (IndexOutOfBoundsException e) {
                    ui.showInvalidNumberError();
                }
                break;

            case "reset":
                tasks.resetTaskList();
                ui.showTaskResetText();
                break;

            case "save":
                try {
                    dbHandler.saveToFile(tasks.getTaskList());
                    ui.showFileSavedText();
                } catch (IOException e) {
                    ui.showFileSaveError();
                }
                break;

            case "find":
                try {
                    ui.showMessage(tasks.findTask(this.input));
                } catch (LegoException e) {
                    ui.showEmptyDescriptionError("find");
                }
                break;

            default:
                ui.showInvalidCommandError();
                break;
        }
    }

    /**
     * Checks whether the application should continue running.
     * 
     * @return true if the application should continue, false otherwise
     */
    public boolean isRunning() {
        return this.isRunning;
    }
}
