package lego.ui;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

import lego.database.DatabaseHandler;
import lego.exception.LegoException;
import lego.parser.InputParser;
import lego.task.Tasklist;

public class Lego {

    private static Tasklist taskList = new Tasklist();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Boolean isRunning = true;

        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Ready to go shopping? Because I am! :D"
                + " Type something and I will record it for you!";
        String closingText = " Bye. Hope to see you again soon!";
        System.out.println(openingText);

        try {
            taskList.setTaskList(DatabaseHandler.loadFileContents());
        } catch (FileNotFoundException e) {
            System.out.println(" File not found. Starting with an empty task list.");
        }

        while (isRunning) {
            InputParser.getNextLine(in);
            String command = InputParser.getCommand();
            switch (command) {
                case "bye":
                    isRunning = false;
                    System.out.println(closingText);
                    break;
                case "list":
                    taskList.listTasks();
                    break;
                case "mark":
                    try {
                        taskList.markTask(InputParser.getTaskNum());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist. Choose another number.");
                    }
                    break;
                case "unmark":
                    try {
                        taskList.unmarkTask(InputParser.getTaskNum());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist. Choose another number.");
                    }
                    break;
                case "todo":
                    try {
                        taskList.addTodo(InputParser.getInput());
                    } catch (LegoException e) {
                        System.out.println(
                                " Todo activity cannot be empty, ensure a task is written after 'todo'. Thankssssss!");
                    }
                    break;
                case "deadline":
                    try {
                        taskList.addNewDeadline(InputParser.getInput());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                " Add a deadline, or ensure that the deadline is separated from the task with a ' /'. Try again.");
                    }
                    break;
                case "event":
                    try {
                        taskList.addNewEvent(InputParser.getInput());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                " Missing start and/or end duration, or ensure that timings and task are separated from one another with a ' /'. Try again.");
                    }
                    break;
                case "delete":
                    try {
                        taskList.deleteEvent(InputParser.getTaskNum());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist. Choose another number.");
                    }
                    break;
                case "save":
                    try {
                        DatabaseHandler.saveFileContents(taskList.getTaskList());
                    } catch (IOException e) {
                        System.out.println(" Error saving file. Please try again.");
                    }
                    break;
                default:
                    System.out.println(
                            "Invalid command. Please input the instruction again begining with 'todo', 'deadline', 'event', 'mark', 'unmark', 'list' or 'bye'.");
                    break;
            }
        }
        in.close();
    }

}
