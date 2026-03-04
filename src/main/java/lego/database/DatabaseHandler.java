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

/**
 * Handles file I/O operations for persisting and loading tasks.
 * 
 * Manages reading tasks from a file and writing tasks to a file to ensure
 * data persistence across application sessions.
 */
public class DatabaseHandler {

    /**
     * Constructs a DatabaseHandler with the specified file path.
     * 
     * @param filePath the path to the file where tasks are stored
     */
    public DatabaseHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads task data from the file.
     * 
     * @return a list of tasks loaded from the file
     * @throws FileNotFoundException if the specified file does not exist
     */
    public ArrayList<Task> loadFileContents() throws FileNotFoundException {
        File f = new File(this.filePath);
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

    /**
     * Saves the provided tasks to the file.
     * 
     * @param tasks the list of tasks to save
     */
    public void saveToFile(ArrayList<Task> tasks) throws IOException {
        String lineEntry = "";
        for (Task task : tasks) {
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
        this.writeToFile(lineEntry);

    }

    public String getFilePath() {
        return this.filePath;
    }

    public void writeToFile(String textToAdd) throws IOException {
        File file = new File(this.filePath);
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private String filePath;
}
