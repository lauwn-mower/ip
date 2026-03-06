package auntie;


import auntie.startup.Storage;
import auntie.task.AuntieException;
import auntie.task.TaskList;

public class Auntie {

    private Storage storage;
    private TaskList tasks;

    public Auntie(String filePath) {
        storage = new Storage(filePath);

        // File loading process:
        // Try the block that loads assuming existing user, and Catch the error that occurs if user is new
        try {
            //
            tasks = new TaskList(storage.loadFile());
        } catch (AuntieException e) {
            tasks = new TaskList();
        }
    }

    // Main method
    public void run() {
        //TODO: make Storage class, Ui Class, Parser Class
    }

    public static void main(String[] args) {
//        new Auntie("data/tasks.txt").run();
    }
}
