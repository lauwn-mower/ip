public class Task {

    // Task object comprises its description and isDone status
    protected String description;
    protected boolean isDone;

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

    public String toStringTaskStatus(){
        return "[" + getStatusIcon() + "] ";
    }

}
