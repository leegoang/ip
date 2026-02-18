package lego.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import lego.task.Event;
import lego.task.Deadline;
import lego.task.Task;
import lego.task.Todo;

import java.util.ArrayList;

public class DatabaseHandler {
    private static String filePath = "../data/lego.txt";

    public DatabaseHandler(String filePath) {
        DatabaseHandler.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public static void writeToFile(String textToAdd) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void saveFileContents(ArrayList<Task> taskList) throws IOException {
        String lineEntry = "";
        for (Task task : taskList) {
            String taskType = "";
            if (task instanceof Todo) {
                taskType = "T";
            } else if (task instanceof Deadline) {
                taskType = "D";
            } else if (task instanceof Event) {
                taskType = "E";
            }
            String isDone = task.isDone() ? "1" : "0";
            lineEntry += taskType + " | " + isDone + " | " + task.getDescription();
            if (task instanceof Deadline) {
                lineEntry += " | " + ((Deadline) task).getBy();
            } else if (task instanceof Event) {
                lineEntry += " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo();
            }
            lineEntry += "\n";
        }
        writeToFile(lineEntry);

    }

    public static ArrayList<Task> loadFileContents() throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<Task>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineArr = line.split("\\|");
            String taskType = lineArr[0].trim();
            String isDone = lineArr[1].trim();
            String description = lineArr[2].trim();
            switch (taskType) {
                case "T":
                    Todo newTodo = new Todo(description);
                    newTodo.setDone(isDone.equals("1") ? true : false);
                    taskList.add(newTodo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(description, lineArr[3]);
                    newDeadline.setDone(isDone.equals("1") ? true : false);
                    taskList.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(description, lineArr[3], lineArr[4]);
                    newEvent.setDone(isDone.equals("1") ? true : false);
                    taskList.add(newEvent);
                    break;
                default:
                    System.out.println("Invalid task type in file: " + taskType);
                    break;
            }
        }
        s.close();
        return taskList;
    }

}
