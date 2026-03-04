package lego.command;

import java.io.IOException;

import lego.ui.Ui;
import lego.exception.LegoException;
import lego.task.Tasklist;
import lego.database.DatabaseHandler;

public class Command {

    private String command;
    private int taskNum;
    private String input;
    private boolean isRunning = true;

    public Command(String command, String input, int taskNum) {
        this.command = command;
        this.input = input;
        this.taskNum = taskNum;
    }

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
                    dbHandler.saveFileContents(tasks.getTaskList());
                } catch (IOException e) {
                    ui.showFileSaveError();
                }
                break;
            case "find":
                tasks.findTask(this.input);
                break;
            default:
                ui.showInvalidCommandError();
                break;
        }
    }

    public boolean isRunning() {
        return this.isRunning;
    }
}
