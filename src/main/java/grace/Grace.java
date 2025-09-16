package grace;

import java.util.ArrayList;
import java.util.Scanner;

public class Grace {
    private final static String PARTITION = "____________________________________________________________";
    private static final String FILE_PATH = "./data/grace.text";
    private static ArrayList<Task> tasks;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks = storage.load();

        greet();

        while (true) {

            String input = sc.nextLine();

            try {

                if (input.equals("bye")) {
                    bye();
                    break;
                } else if (input.equals("list")) {
                    handleList();
                } else if (input.startsWith("mark ")) {
                    handleMark(input);
                    storage.save(tasks);
                } else if (input.startsWith("unmark ")) {
                    handleUnmark(input);
                    storage.save(tasks);
                } else if (input.startsWith("delete ")) {
                    handleDelete(input);
                    storage.save(tasks);
                } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                    handleAdd(input);
                    storage.save(tasks);
                } else {
                    throw new GraceException("Hmm! I don't understand that command!");
                }
            } catch (GraceException e) {
                showError(e.getMessage());
            }
        }
        sc.close();
    }

    private static void greet() {
        printLine();
        printMessage("Hello! I'm grace.Grace!!");
        printMessage("What can I do for you?");
        printLine();
    }

    private static void handleList() {
        printLine();
        if (tasks.isEmpty()) {
            printMessage("Your task list is empty.");
        } else {
            printMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                printMessage(" " + (i + 1) + ". " + tasks.get(i));
            }
        }
        printLine();
    }

    private static void handleMark(String input) throws GraceException {
        int index = parseIndex(input, tasks.size());
        if (index == -1) {
            throw new GraceException("That's not a valid task number to mark");
        }
        tasks.get(index).mark();
        printTaskUpdate("Nice! I've marked this task as done:", tasks.get(index));
    }

    private static void handleUnmark(String input) throws GraceException {
        int index = parseIndex(input, tasks.size());
        if (index == -1) {
            throw new GraceException("That's not a valid task number to unmark");
        }
        tasks.get(index).unmark();
        printTaskUpdate("OK, I've marked this task as not done yet:", tasks.get(index));
    }

    private static void handleDelete(String input) throws GraceException {
        int index = parseIndex(input, tasks.size());
        if (index == -1) {
            throw new GraceException("That's not a valid task number to delete");
        }
        Task removed = tasks.remove(index);
        printLine();
        printMessage("Noted. I've removed this task:");
        printMessage(" " + removed);
        printMessage("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    private static void printTaskUpdate(String message, Task task) {
        printLine();
        printMessage(message);
        printMessage(" " + task);
        printLine();
    }


    private static void handleAdd(String input) throws GraceException{
        Task task = null;

        if (input.startsWith("todo")) {
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new GraceException("You can’t add a todo without a description.");
            }
            task = new Todo(description);
        } else if (input.startsWith("deadline")) {
            String[] parts = input.substring(8).split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                throw new GraceException("A deadline need both a description and a /by time.");
            }
            task = new Deadline(parts[0].trim(), parts[1].trim());
        } else if (input.startsWith("event")) {
            String[] parts = input.substring(5).split("/from |/to ");
            if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                throw new GraceException("An event needs a description, /from time, and a /to time.");
            }
            task = new Event(parts[0].trim(), parts[1].trim(), parts[2]);
        }

        tasks.add(task);
        printLine();
        printMessage("Got it. I've added this task:");
        printMessage(" " + task);
        printMessage("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }


    private static void bye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
        printLine();
    }

    private static int parseIndex(String input, int taskCount) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            if (index >= 0 && index < taskCount) {
                return index;
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

    private static void printLine() {
        System.out.println(PARTITION);
    }

    private static void printMessage(String msg) {
        System.out.println(" " + msg);
    }

    private static void showError(String errorMsg) {
        printLine();
        printMessage(errorMsg);
        printLine();
    }
}
