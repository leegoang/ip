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
            if (command.toLowerCase().equals("bye")) {
                isRunning = false;
                System.out.println(closingText);
            } else if (command.toLowerCase().equals("list")) {
                for (int i = 0; i < Task.getNumOfTasks(); i++) {
                    Task currTask = taskList[i];
                    System.out.println(" " + Integer.toString(currTask.taskNum)
                            + ". [" + currTask.getStatusIcon() + "] "
                            + currTask.getDescription());
                }
            } else if (command.toLowerCase().equals("mark")) {
                Task task = taskList[Integer.parseInt(splitCommand[1]) - 1];
                task.setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + task.getStatusIcon() + "] "
                        + task.getDescription());
            } else if (command.toLowerCase().equals("unmark")) {
                Task task = taskList[Integer.parseInt(splitCommand[1]) - 1];
                task.setDone(false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" [" + task.getStatusIcon() + "] "
                        + task.getDescription());
            } else {
                System.out.println(" added: " + line);
                Task task = new Task(line);
                taskList[Task.getNumOfTasks() - 1] = task;
            }
        }
        in.close();
    }
}
