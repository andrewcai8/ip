package grace;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws GraceException;

    public boolean isExit() {
        return false;
    }
}
