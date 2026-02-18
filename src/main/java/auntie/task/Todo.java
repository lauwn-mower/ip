package auntie.task;

// A Task without any date / time attached to it
public class Todo extends Task {

    public Todo(String description){
        super(description);
        this.type = "T";
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}