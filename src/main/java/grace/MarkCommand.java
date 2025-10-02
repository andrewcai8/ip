package grace;

/**
 * Represents a command that marks a task as completed in the task list.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a MarkCommand with the given task index.
     *
     * @param index the index of the task to mark as done (0 based)
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command by marking the specified task as done,
     * showing a confirmation message, and saving the updated task list.
     *
     * @param tasks   the task list of which the command operates
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to load or save tasks
     * @throws GraceException if the proved index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GraceException {
        if (index < 0 || index >= tasks.size()) {
            throw new GraceException("That's not a valid task number to mark");
        }
        tasks.get(index).mark();
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(" " + tasks.get(index));
        storage.save(tasks.getAll());
    }
}
