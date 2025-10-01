package grace;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMessage("Got it, I've added this task:");
        ui.showMessage(" " + task);
        ui.showMessage("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks.getAll());
    }
}
