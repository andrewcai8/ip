package grace;

/**
 * Represents a command that deletes a task from a task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a DeleteCommand with the specified task index.
     *
     * @param index the index of the task to delete (0 based)
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task from the
     * task list based on the index, showing a confirmation message to the user,
     * and saving the updated task list.
     *
     * @param tasks   the task list of which the command operates
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to load or save tasks
     * @throws GraceException if the proved index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GraceException {
        if (index < 0 || index >= tasks.size()) {
            throw new GraceException("That's not a valid task number to delete");
        }
        Task removed = tasks.remove(index);
        ui.showMessage("Noted, I've removed this task:");
        ui.showMessage(" " + removed);
        ui.showMessage("Now you have: " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAll());
    }
}
