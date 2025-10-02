package grace;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides methods to manipulate and query them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList initialized with the given tasks.
     *
     * @param tasks the initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Finds and returns all tasks whose descriptions contain the given keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }
}
