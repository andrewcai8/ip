package grace;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
