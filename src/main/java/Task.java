public class Task {
    protected String description;
    protected boolean isDone;
    protected int taskNum;

    protected static int numOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numOfTasks++;
        this.taskNum = numOfTasks;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    // ...
}
