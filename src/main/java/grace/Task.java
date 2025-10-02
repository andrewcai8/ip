package grace;

/**
 * Represents a generic task with a description and completion status.
 * Base class for specific types of tasks.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates a task with a given description.
     * Task is initially marked as not done
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of a task,
     * showing its completion status and description
     *
     * @return the string representation of a task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}