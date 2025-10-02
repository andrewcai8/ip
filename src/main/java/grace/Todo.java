package grace;

/**
 * Represents a simple task without a specific time or deadline.
 * A Todo only has a description.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with the given description
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task,
     * including its type and description.
     *
     * @return the string represenation of this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
