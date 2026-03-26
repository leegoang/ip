package lego.task;

/**
 * Represents an event task with a description, start time, and end time.
 * 
 * An Event is a type of Task that spans a period defined by a "from" and "to"
 * time.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the given description, start time, and end
     * time.
     *
     * @param description the description of the task
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of this event.
     *
     * @return the start time as a string
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Sets the start time of this event.
     *
     * @param from the new start time to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Returns the end time of this event.
     *
     * @return the end time as a string
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Sets the end time of this event.
     *
     * @param to the new end time to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns the string representation of the event task.
     *
     * Includes the task type, completion status, description,
     * and the time range of the event.
     *
     * @return a formatted string representing the event task
     */
    @Override
    public String toString() {
        return " [E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
