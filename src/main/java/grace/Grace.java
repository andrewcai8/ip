package grace;

public class Grace {
    private static final String FILE_PATH = "./data/grace.txt";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Grace(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GraceException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Grace(FILE_PATH).run();
    }
}