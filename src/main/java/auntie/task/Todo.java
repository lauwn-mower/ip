package auntie.task;

/**
 * Represents a basic task without any date or time constraints.
 */
public class Todo extends Task {

    public Todo(String description){
        super(description);
        this.type = "T";
    }

    /**
     * Returns the task data formatted for a text file.
     * Format: T | Status | Description (e.g., "T | 0 | IP").
     * @return A pipe-delimited string representing the task.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0")
                + " | " + description;
    }
}