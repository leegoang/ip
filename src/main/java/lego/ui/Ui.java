package lego.ui;

/**
 * Handles all user interface interactions.
 * 
 * Responsible for displaying messages, prompts, and error messages
 * to the user.
 */
public class Ui {

    /**
     * Displays the opening message when the application starts.
     */
    public void showOpeningText() {
        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Ready to go shopping? Because I am! :D\n"
                + " Type something and I will record it for you!";
        System.out.println(openingText);
    }

    /**
     * Displays the closing message when the application exits.
     */
    public void showClosingText() {
        String closingText = " Bye. Hope to see you again soon!";
        System.out.println(closingText);
    }

    /**
     * Displays a generic message to the user.
     *
     * Used to print messages returned from other classes such as Tasklist.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays confirmation when a task is added.
     */
    public void showAddTaskText() {
        System.out.println(" Got it. I've added this task:");
    }

    /**
     * Displays confirmation when a task is deleted.
     */
    public void showDeleteTaskText() {
        System.out.println(" Noted. I've removed this task:");
    }

    /**
     * Displays confirmation that the file has been saved successfully.
     */
    public void showFileSavedText() {
        System.out.println(" File saved successfully.");
    }

    /**
     * Displays confirmation that the task list has been reset.
     */
    public void showTaskResetText() {
        System.out.println(" Task list has been reset. Starting fresh!");
    }

    /**
     * Displays an error message when the data file is missing.
     */
    public void showFileMissingError() {
        System.out.println(" File not found. Starting with an empty task list.");
    }

    /**
     * Displays an error message when a command has an empty description.
     *
     * @param command the command that caused the error
     */
    public void showEmptyDescriptionError(String command) {
        System.out.println(" " + command + " activity cannot be empty, ensure a task is written after '"
                + command + "'. Thankssssss!");
    }

    /**
     * Displays an error message for invalid commands.
     */
    public void showInvalidCommandError() {
        System.out.println(
                "Invalid command. Please input the instruction again beginning with "
                        + "'todo', 'deadline', 'event', 'mark', 'unmark', 'list', 'reset', 'find' or 'bye'.");
    }

    /**
     * Displays an error message when an invalid task number is provided.
     */
    public void showInvalidNumberError() {
        System.out.println("Task does not exist. Choose another number.");
    }

    /**
     * Displays an error message when saving to file fails.
     */
    public void showFileSaveError() {
        System.out.println(" Error saving file. Please try again.");
    }

    /**
     * Displays an error message for invalid deadline format.
     */
    public void showDeadlineFormatError() {
        System.out.println(
                " Add a deadline, or ensure that the deadline is separated from the task with a ' /'. Try again.");
    }

    /**
     * Displays an error message for invalid event format.
     */
    public void showEventFormatError() {
        System.out.println(
                " Missing start and/or end duration, or ensure that timings and task are separated from one another with a ' /'. Try again.");
    }
}
