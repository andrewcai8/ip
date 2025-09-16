package grace;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating storage file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            Task task;
            switch (type) {
            case "T":
                task = new Todo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                task = new Event(parts[2], parts[3], parts[4]);
                break;
            default:
                return null;
            }
            if (isDone) {
                task.mark();
            }
            return task;
        } catch (Exception e) {
            System.out.println("Corrupted line skipped: " + line);
            return null;
        }
    }

    public void save(ArrayList<Task> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                bw.write(formatTask(task));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private String formatTask(Task task) {
        String type = task instanceof Todo ? "T" :
                      task instanceof Deadline ? "D" :
                      task instanceof Event ? "E" : "?";
        String done = task.isDone() ? "1" : "0";

        if (task instanceof Todo) {
            return type + " | " + done + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            return type + " | " + done + " | " +
                    ((Deadline) task).getDescription() + " | " +
                    ((Deadline) task).getBy();
        } else if (task instanceof Event) {
            return type + " | " + done + " | " +
                    ((Event) task).getDescription() + " | " +
                    ((Event) task).getFrom() + " | " +
                    ((Event) task).getTo();
        }
        return "";
    }
}
