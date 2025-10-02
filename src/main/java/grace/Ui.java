package grace;

import java.util.Scanner;

/**
 * Handles user interaction for the Grace application
 * Provides methods to display messages, errors, and read user input.
 */
public class Ui {
    private final static String PARTITION = "____________________________________________________________";
    private final Scanner sc;

    /**
     * Creates an Ui instance with a Scanner for reading user input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(PARTITION);
    }

    public void showMessage(String message) {
        System.out.println(" " + message);
    }

    public void showError(String errorMessage) {
        showMessage(errorMessage);
    }

    public void showWelcome() {
        showLine();
        showMessage("Hello!! I'm Grace!");
        showMessage("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon.");
    }

    /**
     * Reads a line from the user input from the console.
     *
     * @return the user input as a string.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }
}
