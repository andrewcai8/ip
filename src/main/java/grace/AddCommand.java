package grace;

/**
 * Represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Creates an AddCommand with the specified task.
     *
     * @param task the task to be added to the task list
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the add command by adding the task to the task list,
     * showing confirmation message to the user, and saving the updated task list.
     *
     * @param tasks   the task list to add the tasks into
     * @param ui      the user interface to display messages
     * @param storage the storage handler used to save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage("Got it, I've added this task:");
        ui.showMessage(" " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAll());
    }
}
