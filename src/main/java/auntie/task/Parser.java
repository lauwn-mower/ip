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
                handleTodo(taskDescription, tasks, ui, storage);
                break;

            case CMD_DEADLINE:
                handleDeadline(taskDescription, tasks, ui, storage);
                break;

            case CMD_EVENT:
                handleEvent(taskDescription, tasks, ui, storage);
                break;

            case CMD_DELETE:
                handleDelete(taskDescription, tasks, ui, storage);
                break;

            case CMD_MARK:
                handleMark(taskDescription, tasks, ui, storage);
                break;

            case CMD_UNMARK: handleMark(taskDescription, tasks, ui, storage);
                handleUnmark(taskDescription, tasks, ui, storage);
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
    private void handleTodo(String taskDesc, TaskList tasks, Ui ui, Storage storage) throws IOException {
        notEmptyDescription(taskDesc);

        Task newTask = new Todo(taskDesc);
        addTaskAndSave(tasks, ui, storage, newTask);
    }

    private void handleDeadline(String taskDesc, TaskList tasks, Ui ui, Storage storage) throws IOException {
        // Expected format: <description> by <date/time>
        int byIndex = taskDesc.indexOf("by ");

        // If "by " is missing or at the very start (no description)
        boolean wrongFormat = (byIndex <= 0);
        if (wrongFormat) {
            ui.printError("Aiyo, you forgot the 'by'! How I know when is the deadline?");
            return;
        }

        // From taskDesc, identify taskName, date/time
        String deadlineName = taskDesc.substring(0, byIndex).trim();
        String by = taskDesc.substring(byIndex + 3).trim();

        if (deadlineName.isEmpty() || by.isEmpty()) {
            ui.printError("Eh, you cannot leave the task or the time empty lah.");
            return;
        }

        Task t = new Deadline(deadlineName, by);
        addTaskAndSave(tasks, ui, storage, t);
    }

    private void handleEvent(String taskDesc, TaskList tasks, Ui ui, Storage storage) throws IOException {
        notEmptyDescription(taskDesc);

        // Expected format: description from startTime to endTime
        // From taskDesc, identify taskName, startTime, endTime
        int fromIndex = taskDesc.indexOf("from");
        int toIndex = taskDesc.indexOf("to");
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(); // Auntie will say "formatting wrong lah"
        }

        String eventName = taskDesc.substring(0, fromIndex).trim();
        String from = taskDesc.substring(fromIndex + 4, toIndex).trim();
        String to = taskDesc.substring(toIndex + 2).trim();

        Task t = new Event(eventName, from, to);
        addTaskAndSave(tasks, ui, storage, t);
    }

    private void handleMark(String taskDesc, TaskList tasks, Ui ui, Storage storage) throws IOException {
        // Identify task to be marked
        int idx = Integer.parseInt(taskDesc) - 1;
        boolean isDone = tasks.getTask(idx).isDone();

        // To mark task, first check that it's unmarked
        // If already marked, inform user
        if (!isDone) {
            tasks.markTask(idx);
            System.out.println("Wah u finally stopped lazing around. Good good");
            return;
        } else {
            System.out.println("Eh, you mark already. You want remove?");
        }

        printUpdatedTask(tasks, storage, idx);
    }

    private void handleDelete(String taskDesc, TaskList tasks, Ui ui, Storage storage) throws IOException {
        notEmptyDescription(taskDesc);

        try {
            // Convert the String argument to an index
            int taskIndex = Integer.parseInt(taskDesc) - 1;

            // TaskList class removes the task
            // Return removedTask to be announced
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.printDeletedTask(removedTask, tasks.getSize());

            storage.saveFile(tasks);

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.printError("Aiyo, which task you want delete? Give me a proper number leh.");
        }
    }

    private void handleUnmark(String taskDesc, TaskList tasks, Ui ui, Storage storage) throws IOException {
        // Identify task to be unmarked
        int idx = Integer.parseInt(taskDesc) - 1;
        boolean isDone = tasks.getTask(idx).isDone();

        // To unmark task, first check if it's marked
        if (isDone) {
            tasks.unmarkTask(idx);
            System.out.println("U lie to me issit? Want cheat horrr. But ok good that u own up");
            return;
        } else {
            System.out.println("Hoi, you unmarked already");
        }

        printUpdatedTask(tasks, storage, idx);
    }

    /*
     * Section: Helper functions
     */
    private static void notEmptyDescription(String desc) {
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("Wat you waaaant. Can specify onot?");
        }
    }

    private static void addTaskAndSave(TaskList tasks, Ui ui, Storage storage, Task t) throws IOException {
        tasks.addTask(t);
        ui.printAddedTask(t, tasks.getSize());
        storage.saveFile(tasks);
    }

    private static void printUpdatedTask(TaskList tasks, Storage storage, int idx) throws IOException {
        System.out.println(tasks.getTask(idx).toStringTaskIcons() + tasks.getTask(idx).description);
        storage.saveFile(tasks);
    }
}