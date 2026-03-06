package auntie.task;

import auntie.startup.Storage;
import java.io.IOException;

public class Parser {

    // Commands, taken from auntietasker
    public static final String CMD_LIST = "list";
    public static final String CMD_EXIT = "bye";
    public static final String CMD_MARK = "mark";
    public static final String CMD_UNMARK = "unmark";
    public static final String CMD_TODO = "todo";
    public static final String CMD_DEADLINE = "deadline";
    public static final String CMD_EVENT = "event";
    public static final String CMD_DELETE = "delete";

    public boolean parse(String fullCommand, TaskList tasks, Ui ui, Storage storage) {
        // Split the input into command and description
        // Command is the first word of the input, else taskDescription
        String[] splitInput = fullCommand.split(" ", 2);
        String commandWord = splitInput[0].toLowerCase();
        String taskDescription = (splitInput.length > 1) ? splitInput[1] : ""; // Check that desc is not empty

        try {
            switch (commandWord) {
            case CMD_EXIT:
                // Stop run(), save and close
                return true;

            case CMD_LIST:
                ui.printList(tasks);
                break;

            case CMD_TODO:
                handleTodo(taskDescription);
                break;

            case CMD_DEADLINE:
                handleDeadline(taskDescription);
                break;

            case CMD_EVENT:
                handleEvent(taskDescription);
                break;

            case CMD_DELETE:
                handleDelete(taskDescription);
                break;

            case CMD_MARK:
                handleMark(taskDescription);
                break;

            case CMD_UNMARK:
                handleUnmark(taskDescription);
                break;

            default:
                // If no known commands used, clarify with user
                ui.printError("I dun understand you.");
                break;
            }
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }

        // Keep looping if false, since true=exitProgram
        return false;
    }


    /*
     * Section: handleTask functions to decode task details and add to taskList
     */
    private void handleTodo(String taskDesc)  {
    }

    private void handleDeadline(String taskDesc) {
    }

    private void handleEvent(String taskDesc) {
    }

    private void handleDelete(String taskDesc) {
    }

    private void handleMark(String taskDesc) {
    }

    private void handleUnmark(String taskDesc) {
    }

}