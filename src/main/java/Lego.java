import java.util.Scanner;

public class Lego {

    private static int MAX_TASKS = 100;
    private static Task[] taskList = new Task[MAX_TASKS];

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Boolean isRunning = true;

        String openingText = " Hello! I'm [Lego]\n"
                + " What can I do for you?\n"
                + " Ready to go shopping? Because I am! :D"
                + " Type something and I will record it for you!";
        String closingText = " Bye. Hope to see you again soon!";

        System.out.println(openingText);
        while (isRunning) {
            line = in.nextLine();
            String[] splitCommand = line.split(" ");
            String command = splitCommand[0];
            switch (command) {
                case "bye":
                    isRunning = false;
                    System.out.println(closingText);
                    break;
                case "list":
                    for (int i = 0; i < Task.getNumOfTasks(); i++) {
                        Task currTask = taskList[i];
                        System.out.println(" " + Integer.toString(currTask.taskNum) + " "
                                + taskList[i]);
                    }
                    break;
                case "mark":
                    Task toMarkTask = taskList[Integer.parseInt(splitCommand[1]) - 1];
                    toMarkTask.setDone(true);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(toMarkTask);
                    break;
                case "unmark":
                    Task task = taskList[Integer.parseInt(splitCommand[1]) - 1];
                    task.setDone(false);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    break;
                case "todo":
                    try {
                        addTodo(line);
                    } catch (LegoException e) {
                        System.out.println(
                                " Todo activity cannot be empty, ensure a task is written after 'todo'. Thankssssss!");
                    }
                    break;
                case "deadline":
                    System.out.println(" Got it. I've added this task:");
                    String[] splitDeadline = line.replace("deadline", "").split(" /");
                    String deadlineOnly = splitDeadline[0];
                    String taskDeadline = splitDeadline[1];
                    Task newDeadline = new Deadline(deadlineOnly, taskDeadline);
                    taskList[Task.getNumOfTasks() - 1] = newDeadline;
                    System.out.println(newDeadline);
                    System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
                    break;
                case "event":
                    System.out.println(" Got it. I've added this task:");
                    String[] splitEvent = line.replace("event", "").split(" /");
                    String eventOnly = splitEvent[0];
                    String eventFrom = splitEvent[1];
                    String eventTo = splitEvent[2];
                    Task newEvent = new Event(eventOnly, eventFrom, eventTo);
                    taskList[Task.getNumOfTasks() - 1] = newEvent;
                    System.out.println(newEvent);
                    System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
                    break;
                default:
                    System.out.println(
                            "Invalid command. Please input the instruction again begining with 'todo', 'deadline', 'event', 'mark', 'unmark', 'list' or 'bye'.");
                    break;
            }
        }
        in.close();
    }

    private static void addTodo(String input) throws LegoException {
        String todoOnly = input.replace("todo", "");
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
