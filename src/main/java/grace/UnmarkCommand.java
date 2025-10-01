package grace;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

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
