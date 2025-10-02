package grace;

/**
 * Represents a task with a deadline.
 * A deadline has a description and a date/time it must be complete.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline with a specified description and due date by.
     *
     * @param description the description of the deadline task
     * @param by          the due date/time of the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task including,
     * the type, description, and due date.
     *
     * @return the string representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
