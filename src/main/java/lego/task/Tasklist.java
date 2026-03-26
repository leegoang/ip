package lego.task;

import java.util.ArrayList;

import lego.exception.LegoException;

/**
 * Represents a list of tasks and provides operations to manage them.
 * 
 * Handles adding, removing, updating, listing, and searching tasks.
 * All methods return formatted messages instead of printing directly,
 * allowing the Ui class to handle output.
 */
public class Tasklist {

    private ArrayList<Task> taskList;

    /**
     * Constructs an empty Tasklist.
     */
    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task at the specified index.
     *
     * @param index the index of the task to remove
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public void removeTask(int index) {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Task does not exist.");
        }
    }

    /**
     * Resets the task list to an empty list.
     *
     * @return confirmation message
     */
    public String resetTaskList() {
        this.taskList = new ArrayList<>();
        return "Task list has been reset.";
    }

    /**
     * Returns the list of tasks.
     *
     * @return the task list
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Replaces the current task list with the given list.
     *
     * @param taskList the new list of tasks
     */
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index the index of the task to mark
     * @return confirmation message
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public String markTask(int index) {
        if (index >= 0 && index < this.taskList.size()) {
            Task task = this.taskList.get(index);
            task.setDone(true);
            return "Nice! I've marked this task as done:\n " + task;
        } else {
            throw new IndexOutOfBoundsException("Task does not exist.");
        }
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param index the index of the task to unmark
     * @return confirmation message
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public String unmarkTask(int index) {
        if (index >= 0 && index < this.taskList.size()) {
            Task task = this.taskList.get(index);
            task.setDone(false);
            return "OK, I've marked this task as not done yet:\n " + task;
        } else {
            throw new IndexOutOfBoundsException("Task does not exist.");
        }
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the index of the task to delete
     * @return confirmation message
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public String deleteTask(int index) {
        if (index >= 0 && index < this.taskList.size()) {
            Task taskToDelete = this.taskList.get(index);
            this.taskList.remove(index);

            return "Noted. I've removed this task:\n "
                    + taskToDelete
                    + "\nNow you have " + this.taskList.size() + " tasks in the list.";
        } else {
            throw new IndexOutOfBoundsException("Task does not exist.");
        }
    }

    /**
     * Parses input and adds a new Todo task.
     *
     * @param input the user input string
     * @return confirmation message
     * @throws LegoException if the description is invalid
     */
    public String addTodo(String input) throws LegoException {
        String desc = input.trim();

        if (desc.isEmpty()) {
            throw new LegoException();
        }

        Task newTodo = new Todo(desc);
        this.taskList.add(newTodo);

        return "Got it. I've added this task:\n "
                + newTodo
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
    }

    /**
     * Parses input and adds a new Deadline task.
     *
     * Expected format: description /by <deadline>
     *
     * @param input the user input string
     * @return confirmation message
     * @throws LegoException if the input format is invalid
     */
    public String addNewDeadline(String input) throws LegoException {
        String[] split = input.trim().split(" /");

        if (split.length < 2) {
            throw new LegoException();
        }

        String desc = split[0].trim();
        String by = split[1].trim();

        Deadline d = new Deadline(desc, by);
        this.taskList.add(d);

        return "Got it. I've added this task:\n "
                + d
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
    }

    /**
     * Parses input and adds a new Event task.
     *
     * Expected format: description /from <start> /to <end>
     *
     * @param input the user input string
     * @return confirmation message
     * @throws LegoException if the input format is invalid
     */
    public String addNewEvent(String input) throws LegoException {
        String[] split = input.trim().split(" /");

        if (split.length < 3) {
            throw new LegoException();
        }

        String desc = split[0].trim();
        String from = split[1].replace("from", "").trim();
        String to = split[2].replace("to", "").trim();

        Event e = new Event(desc, from, to);
        this.taskList.add(e);

        return "Got it. I've added this task:\n "
                + e
                + "\nNow you have " + this.taskList.size() + " tasks in the list.";
    }

    /**
     * Returns a formatted list of all tasks.
     *
     * @return string representation of all tasks
     */
    public String listTasks() {
        if (this.taskList.isEmpty()) {
            return " Your task list is currently empty.";
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < this.taskList.size(); i++) {
            sb.append(" ")
                    .append(i + 1)
                    .append(". ")
                    .append(this.taskList.get(i))
                    .append("\n");
        }

        return sb.toString();
    }

    /**
     * Searches for tasks containing the given keyword.
     *
     * @param input the keyword to search for
     * @return matching tasks or a message if none found
     * @throws LegoException if the keyword is invalid
     */
    public String findTask(String input) throws LegoException {
        String keyword = input.trim();

        if (keyword.isEmpty()) {
            throw new LegoException();
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks:\n");
        boolean found = false;

        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = this.taskList.get(i);

            if (t.getDescription().contains(keyword)) {
                found = true;
                sb.append(" ")
                        .append(i + 1)
                        .append(". ")
                        .append(t)
                        .append("\n");
            }
        }

        if (!found) {
            return "No matching tasks found.";
        }

        return sb.toString();
    }
}
