package auntie.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    /*
     * TaskList constructors
     */
    // Construct task list for a NEW user
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Construct task list for an EXISTING user
    // To be used with file retrieval function in main()
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /*
     * Section: Methods dealing with editing task list
     */
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

    // Return list of a new ArrayList<Task> built from tasks containing keyword
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    /*
     * Section: Helper functions to aid logic in tasklist functions
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