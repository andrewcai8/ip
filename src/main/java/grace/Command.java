package grace;

/**
 * Represents an abstract command that can be executed in the Grace application
 * Each command defines its own behavior when executed.
 */
public abstract class Command {

    /**
     * Executes the command with the given tasklist, user interface, and storage handler.
     *
     * @param tasks   the task list of which the command operates
     * @param ui      the user interface used to display messages
     * @param storage the storage handler used to load or save tasks
     * @throws GraceException if the command encounters an error during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GraceException;

    /**
     * Determines whether this command signals the application to exit
     *
     * @return true if the application should exit, otherwise false
     */
    public boolean isExit() {
        return false;
    }
}
