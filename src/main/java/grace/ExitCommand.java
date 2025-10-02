package grace;

/**
 * Represents a command that exits the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by showing the goodbye message
     *
     * @param tasks   the task list of which the command operates
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to load or save tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command will cause the application to exit.
     *
     * @return true, causing application to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
