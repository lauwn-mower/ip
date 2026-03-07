package auntie;

import auntie.task.TaskList;
import auntie.task.AuntieException;
import auntie.startup.Storage;

/**
 * Represents the main entry point for the Auntie chatbot application.
 * Coordinates the interaction between the user interface, task storage,
 * and command parsing.
 * * @author lauwn-mower
 * @version v0.2
 */
public class Auntie {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes the Auntie application components and loads existing tasks.
     * * @param filePath The path to the file where task data is stored.
     */
    public Auntie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        Ui.printGreeting();

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

    /**
     * Starts the main program loop to read user commands and delegate
     * execution to the Parser.
     */
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

    /**
     * Creates an Auntie instance and runs the application.
     * * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Auntie("data/tasks.txt").run();
    }
}
