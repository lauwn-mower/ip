package auntie.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String dateBy){
        super(description);
        this.by = dateBy;
        this.type = "D";
    }

    /**
     * Returns the list format including the deadline information.
     * Format: [D][Status] description (by: time) (e.g., "[D][ ] IP by: Friday").
     * @return A string formatted for UI display.
     */
    @Override
    public String toStringListFormat(){
        return toStringTaskIcons()
                + this.description
                + " (by: " + this.by + ")";
    }

    /**
     * Returns the Deadline task data formatted for a text file.
     * Format: D | Status | Description | By (e.g., "D | 0 | IP | Friday").
     * @return A pipe-delimited string including the deadline.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + by;
    }
}
