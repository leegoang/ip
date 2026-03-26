package lego.task;

/**
 * Represents a generic task with a description and completion status.
 * 
 * A Task serves as the base class for specific task types such as
 * Todo, Deadline, and Event.
 */
public class Task {

    protected String description;
    protected boolean isDone;
    public int taskNum;

    protected static int numOfTasks = 0;

    /**
     * Constructs a Task with the given description.
     * 
     * Initializes the task as not done and assigns a unique task number.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
        this.taskNum = numOfTasks;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon of this task.
     *
     * "X" indicates the task is completed, while a blank space
     * indicates it is not done.
     *
     * @return the status icon as a string
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Sets the completion status of this task.
     *
     * @param isDone true if the task is completed, false otherwise
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the total number of tasks created.
     *
     * @return the total number of tasks
     */
    public static int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Decreases the total number of tasks by one.
     *
     * Typically used when a task is deleted.
     */
    public static void decreaseNumOfTasks() {
        numOfTasks--;
    }

    /**
     * Returns whether this task is completed.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task.
     *
     * Includes the completion status and description.
     *
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
