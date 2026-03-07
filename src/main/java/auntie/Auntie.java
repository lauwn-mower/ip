package auntie;

import auntie.task.TaskList;
import auntie.task.AuntieException;
import auntie.startup.Storage;

public class Auntie {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    // Constructor for Auntie additionally constructs Ui and Storage object
    // With Storage created, start loading up user's use history
    public Auntie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        Ui.greetUser();

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

    public void run() {
        // A flag to identify start and stop of program
        boolean isExit = false;

        // Parser object to decode user inputs and execute commands
        Parser parser = new Parser();

        // Keep scanning for and decoding user inputs until program termination
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
