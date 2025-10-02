package grace;

/**
 * Represents a task that occurs with a certain timeframe.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an Event with the specified description, start time, and end time.
     *
     * @param description the description of the event
     * @param from        the start time of the event
     * @param to          the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * Returns the string representation of a event task,
     * including its type, description, start time, end time.
     *
     * @return the string representation fo a event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
