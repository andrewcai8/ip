package grace;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

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
