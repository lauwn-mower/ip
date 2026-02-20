package auntie.task;

public class Task {

    // Task object comprises its description and isDone status
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description){
        this.description = description;
        this.isDone = false;
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

    public String toStringTaskIcons(){
        return "[" + this.type + "][" + getStatusIcon() + "] ";
    }

    public String toStringListFormat(){
        return toStringTaskIcons() + this.description;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Common method to convert string to suitable file format
    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return "? | " + status + " | " + description;
    }
}
