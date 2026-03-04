package lego.ui;

public class Ui {

    public void showOpeningText() {
        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Ready to go shopping? Because I am! :D"
                + " Type something and I will record it for you!";
        System.out.println(openingText);
    }

    public void showClosingText() {
        String closingText = " Bye. Hope to see you again soon!";
        System.out.println(closingText);
    }

    public void showFileMissingError() {
        System.out.println(" File not found. Starting with an empty task list.");
    }

    public void showEmptyDescriptionError(String command) {
        System.out.println(" " + command + " activity cannot be empty, ensure a task is written after '" + command
                + "'. Thankssssss!");
    }

    public void showInvalidCommandError() {
        System.out.println(
                "Invalid command. Please input the instruction again begining with 'todo', 'deadline', 'event', 'mark', 'unmark', 'list', 'final' or 'bye'.");
    }

    public void showInvalidNumberError() {
        System.out.println("Task does not exist. Choose another number.");
    }

    public void showFileSaveError() {
        System.out.println(" Error saving file. Please try again.");
    }

    public void showDeadlineFormatError() {
        System.out.println(
                " Add a deadline, or ensure that the deadline is separated from the task with a ' /'. Try again.");
    }

    public void showEventFormatError() {
        System.out.println(
                " Missing start and/or end duration, or ensure that timings and task are separated from one another with a ' /'. Try again.");
    }
}
