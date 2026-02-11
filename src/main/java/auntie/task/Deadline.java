package auntie.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String dateBy){
        super(description);
        this.by = dateBy;
        this.type = "D";
    }

    @Override
    public String toStringListFormat(){
        return toStringTaskIcons() + this.description + " (by: " + this.by + ")";
    }
}
