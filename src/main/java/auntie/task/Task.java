package auntie.task;

/**
 * Represents an abstract task in the Auntie application.
 * Defines the shared properties (description, completion status, type)
 * and the contract for formatting tasks for UI and storage.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Initializes a task with a description and sets completion to false.
     * @param description The text describing the task.
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean doneStatus){
        isDone = doneStatus;
    }

    public String getStatusIcon(){
        return (isDone() ? "X" : " ");
    }

    /**
     * Returns the task type and status icons formatted for UI display.
     * Format: [Type][Status] (e.g., "[T][X]").
     * @return A string containing the categorized icons.
     */
    public String toStringTaskIcons(){
        return "[" + this.type + "][" + getStatusIcon() + "] ";
    }

    /**
     * Returns the formatted string representation for the user-facing list.
     * Format: [Type][Status] description (e.g., "[T][ ] buy kopi").
     * @return A string combining status icons and the task description.
     */
    public String toStringListFormat(){
        return toStringTaskIcons() + this.description;
    }

    /**
     * Returns the string representation formatted for text file storage.
     * Format: Type | Status | Description (e.g., "T | 1 | buy kopi").
     * @return A pipe-delimited string representing the task.
     */
    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return "? | " + status + " | " + description;
    }
}
