import java.util.Scanner;

public class Grace {
    private final static String PARTITION = "____________________________________________________________";
    private final static int TASK_MAX = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[TASK_MAX];
        int taskCount = 0;

        greet();

        while (true) {

            String input = sc.nextLine();

            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                handleList(tasks, taskCount);
            } else if (input.startsWith("mark ")) {
                handleMark(tasks, taskCount, input);
            } else if (input.startsWith("unmark ")) {
                handleUnmark(tasks, taskCount, input);
            } else {
                taskCount = handleAdd(tasks, taskCount, input);
            }
        }
        sc.close();
    }

    private static void greet() {
        printLine();
        printMessage("Hello! I'm Grace!!");
        printMessage("What can I do for you?");
        printLine();
    }

    private static void handleList(Task[] tasks, int taskCount) {
        printLine();
        printMessage("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            printMessage(" " + (i + 1) + ". " + tasks[i]);
        }
        printLine();
    }

    private static void handleMark(Task[] tasks, int taskCount, String input) {
        int index = parseIndex(input, taskCount);
        if (index < taskCount && index >= 0) {
            tasks[index].mark();
            printLine();
            printMessage(" Nice! I've marked this task as done:");
            printMessage(" " + tasks[index]);
            printLine();
        }
    }

    private static void handleUnmark(Task[] tasks, int taskCount, String input) {
        int index = parseIndex(input, taskCount);
        if (index < taskCount && index >= 0) {
            tasks[index].unmark();
            printLine();
            printMessage(" OK, I've marked this task as not done yet:");
            printMessage(" " + tasks[index]);
            printLine();
        }
    }


    private static int handleAdd(Task[] tasks, int taskCount, String input) {
        Task task;

        if (input.startsWith("todo ")){
            String description = input.substring(5);
            task = new Todo(description);

        } else if (input.startsWith("deadline ")) {
            String[] parts = input.substring(9).split("/by", 2);
            task = new Deadline(parts[0].trim(), parts[1].trim());

        } else if (input.startsWith("event ")){
            String[] parts = input.substring(6).split("/from |/to ");
            task = new Event(parts[0].trim(), parts[1].trim(), parts[2]);
        } else {
            task = new Task(input);
        }

        tasks[taskCount] = task;
        taskCount++;
        printLine();
        printMessage("Got it. I've added this task:");
        printMessage(" " + task);
        printMessage("Now you have " + taskCount + " tasks in the list.");
        printLine();
        return taskCount;
    }


    private static void bye() {
        printLine();
        printMessage("Bye. Hope to see you again soon!");
        printLine();
    }

    private static int parseIndex(String input, int taskCount) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index >= 0 && index < taskCount) {
            return index;
        }
        return -1;
    }

    private static void printLine() {
        System.out.println(PARTITION);
    }

    private static void printMessage(String msg) {
        System.out.println(" " + msg);
    }
}
