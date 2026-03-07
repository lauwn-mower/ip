package auntie.task;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to){
        super(description);
        this.type = "E";
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the list format including the event duration.
     * Format: [E][Status] description (from: S to: E) (e.g., "[E][ ] Training from: 7 to: 9").
     * @return A string formatted for UI display.
     */
    @Override
    public String toStringListFormat(){
        return toStringTaskIcons()
                + this.description
                + "(from: " + this.from
                + " to: " + this.to + ")";
    }

    /**
     * Returns the Event task data formatted for a text file.
     * Format: E | Status | Description | From | To (e.g., "E | 0 | Training | 7 | 9").
     * @return A pipe-delimited string including the duration.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0")
                + " | " + description
                + " | " + from
                + " | " + to;
    }
}
