import java.util.Scanner;

public class Lego {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Boolean isRunning = true;
        Task[] taskList = new Task[100];

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
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(toMarkTask);
                    break;
                case "unmark":
                    Task task = taskList[Integer.parseInt(splitCommand[1]) - 1];
                    task.setDone(false);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                    break;
                case "todo":
                    System.out.println(" Got it. I've added this task:");
                    String todoOnly = line.replace("todo ", "");
                    Task newTodo = new Todo(todoOnly);
                    taskList[Task.getNumOfTasks() - 1] = newTodo;
                    System.out.println(newTodo);
                    System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
                    break;
                case "deadline":
                    System.out.println(" Got it. I've added this task:");
                    String[] splitDeadline = line.replace("deadline ", "").split(" /");
                    String deadlineOnly = splitDeadline[0];
                    String taskDeadline = splitDeadline[1];
                    Task newDeadline = new Deadline(deadlineOnly, taskDeadline);
                    taskList[Task.getNumOfTasks() - 1] = newDeadline;
                    System.out.println(newDeadline);
                    System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
                    break;
                case "event":
                    System.out.println(" Got it. I've added this task:");
                    String[] splitEvent = line.replace("deadline ", "").split(" /");
                    String eventOnly = splitEvent[0];
                    String eventFrom = splitEvent[1];
                    String eventTo = splitEvent[2];
                    Task newEvent = new Event(eventOnly, eventFrom, eventTo);
                    taskList[Task.getNumOfTasks() - 1] = newEvent;
                    System.out.println(newEvent);
                    System.out.println(" Now you have " + Task.getNumOfTasks() + " tasks in the list.");
                    break;
                default:
                    System.out.println(" added: " + line);
                    Task newTask = new Task(line);
                    taskList[Task.getNumOfTasks() - 1] = newTask;
                    break;
            }
        }
        in.close();
    }
}
