package grace;

/**
 * Represents a command that unmarks a task in the task list.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates a UnmarkCommand with the given task index.
     *
     * @param index the index of the task to unmark (0 based)
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command by unmarking the specified task,
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
            throw new GraceException("That's not a valid task number to unmark");
        }
        tasks.get(index).unmark();
        ui.showMessage("Okay, I've marked this task as not done yet:");
        ui.showMessage(" " + tasks.get(index));
        storage.save(tasks.getAll());
    }
}
