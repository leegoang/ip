package lego.task;

/**
 * Represents a deadline task with a description and a due date.
 * 
 * A Deadline is a type of Task that includes a "by" field indicating
 * when the task should be completed.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description the description of the task
     * @param by          the due date of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date of this deadline task.
     *
     * @return the due date as a string
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Sets the due date of this deadline task.
     *
     * @param by the new due date to set
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * Includes the task type, completion status, description,
     * and due date.
     *
     * @return a formatted string representing the deadline task
     */
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + this.by + ")";
    }
}
