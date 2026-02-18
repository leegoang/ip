package lego.ui;

import java.util.Scanner;

import lego.task.Deadline;
import lego.task.Event;
import lego.task.Task;
import lego.task.Todo;

public class Lego {

    private static int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Boolean isRunning = true;

        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Ready to go shopping? Because I am! :D"
                + " Type something and I will record it for you!";
        String closingText = " Bye. Hope to see you again soon!";

        System.out.println(openingText);
        while (isRunning) {
            InputParser.getNextLine(in);
            String command = InputParser.getCommand();
            switch (command) {
                case "bye":
                    isRunning = false;
                    System.out.println(closingText);
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    try {
                        markTask(true);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist. Choose another number.");
                    }
                    break;
                case "unmark":
                    try {
                        markTask(false);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist. Choose another number.");
                    }
                    break;
                case "todo":
                    try {
                        addTodo(InputParser.getInput());
                    } catch (LegoException e) {
                        System.out.println(
                                " Todo activity cannot be empty, ensure a task is written after 'todo'. Thankssssss!");
                    }
                    break;
                case "deadline":
                    try {
                        addNewDeadline();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                " Add a deadline, or ensure that the deadline is separated from the task with a ' /'. Try again.");
                    }
                    break;
                case "event":
                    try {
                        addNewEvent();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                " Missing start and/or end duration, or ensure that timings and task are separated from one another with a ' /'. Try again.");
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

    private static void addNewEvent() {
        String[] splitEvent = InputParser.getInput().replace("event", "").split(" /");
        String eventOnly = splitEvent[0].trim();
        String eventFrom = splitEvent[1].trim();
        String eventTo = splitEvent[2].trim();
        Event newEvent = new Event(eventOnly, eventFrom, eventTo);
        System.out.println(" Got it. I've added this task:");
        taskList[Task.getNumOfTasks() - 1] = newEvent;
        System.out.println(newEvent);
        System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
    }

    private static void addNewDeadline() {
        System.out.println(" Got it. I've added this task:");
        String[] splitDeadline = InputParser.getInput().replace("deadline", "").split(" /");
        String deadlineOnly = splitDeadline[0].trim();
        String taskDeadline = splitDeadline[1].trim();
        Deadline newDeadline = new Deadline(deadlineOnly, taskDeadline);
        taskList[Task.getNumOfTasks() - 1] = newDeadline;
        System.out.println(newDeadline);
        System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
    }

    private static void markTask(boolean complete) {
        Task task = taskList[InputParser.getTaskNum() - 1];
        task.setDone(complete);
        if (complete) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
    }

    private static void listTasks() {
        for (int i = 0; i < Task.getNumOfTasks(); i++) {
            Task currTask = taskList[i];
            System.out.println(" " + Integer.toString(currTask.taskNum) + " "
                    + taskList[i]);
        }
    }

    private static void addTodo(String input) throws LegoException {
        String todoOnly = input.replace("todo", "").trim();
        if (todoOnly.replace(" ", "").equals("")) {
            throw new LegoException();
        }
        System.out.println(" Got it. I've added this task:");
        Task newTodo = new Todo(todoOnly);
        taskList[Task.getNumOfTasks() - 1] = newTodo;
        System.out.println(newTodo);
        System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
    }
}
