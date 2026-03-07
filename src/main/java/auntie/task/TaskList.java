package auntie.task;

import java.util.ArrayList;

/**
 * Represents the task list containing the data and operations for task management.
 * Provides methods to add, delete, mark, and search for tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list for a new user.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list using an existing collection of tasks.
     * @param tasks The initial list of tasks retrieved from storage.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public void markTask(int index) {
        tasks.get(index).setDone(true);
    }

    public void unmarkTask(int index) {
        tasks.get(index).setDone(false);
    }

    /**
     * Returns a list of tasks that contain the specified keyword in their description.
     * Case-sensitivity depends on the implementation (typically case-insensitive for Auntie).
     * @param keyword The search term provided by the user.
     * @return An ArrayList containing all tasks that match the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /*
     * Section: Getter and helper functions to aid logic in tasklist functions
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}