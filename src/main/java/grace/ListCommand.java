package grace;

/**
 * Represents a command that lists all the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all the tasks in the task list.
     * If the task list is empty, a corresponding message will be displayed
     *
     * @param tasks   the task list of which the command operates
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to load or save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showMessage("Your task list is empty.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
    }
}