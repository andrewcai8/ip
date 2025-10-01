package grace;

import java.util.Scanner;

public class Ui {
    private final static String PARTITION = "____________________________________________________________";
    private final Scanner sc;

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
        showLine();
        showMessage(errorMessage);
        showLine();
    }

    public void showWelcome() {
        showLine();
        showMessage("Hello!! I'm Grace!");
        showMessage("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        showMessage("Bye. Hope to see you again soon.");
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void close() {
        sc.close();
    }
}
