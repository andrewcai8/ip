package grace;

/**
 * Entry point for the Grace application.
 * Initializes the core components storage, tasks, and ui
 * and runs the main command loop.
 */
public class Grace {
    private static final String FILE_PATH = "./data/grace.txt";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Creates a new Grace application instance.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Grace(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main loop of the application, handles user input
     * until the user calls the exit command.
     */
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

    /**
     * The main entry point of the program
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        new Grace(FILE_PATH).run();
    }
}