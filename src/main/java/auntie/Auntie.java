package auntie;

import auntie.task.TaskList;
import auntie.task.AuntieException;
import auntie.task.Parser;
import auntie.task.Ui;
import auntie.startup.Storage;

public class Auntie {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    // Constructor for Auntie additionally constructs Ui and Storage object
    public Auntie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        // File loading process:
        // Try the block that loads assuming existing user, and Catch the error that occurs if user is new
        try {
            //
            tasks = new TaskList(storage.loadFile());
            ui.printWelcome(false);
        } catch (AuntieException e) {
            ui.printWelcome(true);
            tasks = new TaskList();
        }
    }

    // Main method
    public void run() {
        boolean isExit = false;
        Parser parser = new Parser();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.printLine();

                isExit = parser.parse(fullCommand, tasks, ui, storage);
            } catch (Exception e) {
                ui.printError(e.getMessage());
            } finally {
                ui.printLine();
            }
        }
    }

    public static void main(String[] args) {
        new Auntie("data/tasks.txt").run();
    }
}
