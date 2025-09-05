import java.util.Scanner;

public class Grace {
    private final static String PARTITION = "____________________________________________________________";
    private final static int TASK_MAX = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[TASK_MAX];
        int taskCount = 0;


        System.out.println(PARTITION);
        System.out.println("Hello! I'm Grace!!");
        System.out.println("What can I do for you?");
        System.out.println(PARTITION);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(PARTITION);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(PARTITION);
                break;
            } else if (input.equals("list")) {
                System.out.println(PARTITION);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println(PARTITION);
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index < taskCount && index >= 0) {
                    tasks[index].mark();
                    System.out.println(PARTITION);
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(" " + tasks[index]);
                    System.out.println(PARTITION);
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index < taskCount && index >= 0) {
                    tasks[index].unmark();
                    System.out.println(PARTITION);
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(" " + tasks[index]);
                    System.out.println(PARTITION);
                }
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(PARTITION);
                System.out.println(" added: " + input);
                System.out.println(PARTITION);
            }
        }
        sc.close();
    }
}
