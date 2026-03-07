package auntie;

import auntie.startup.Storage;
import auntie.task.AuntieException;
import auntie.task.Deadline;
import auntie.task.Event;
import auntie.task.Task;
import auntie.task.TaskList;
import auntie.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    // Commands
    public static final String CMD_LIST = "list";
    public static final String CMD_EXIT = "bye";
    public static final String CMD_MARK = "mark";
    public static final String CMD_UNMARK = "unmark";
    public static final String CMD_TODO = "todo";
    public static final String CMD_DEADLINE = "deadline";
    public static final String CMD_EVENT = "event";
    public static final String CMD_DELETE = "delete";
    public static final String CMD_FIND = "find";
    public static final String CMD_HELP = "help";

    // This method takes in inputs and decodes what to execute
    public boolean parse(String fullCommand, TaskList tasks, Ui ui, Storage storage) {
        // Split the input into command and description
        // Command is the first word of the input, else taskDescription
        String[] splitInput = fullCommand.split(" ", 2);
        String commandWord = splitInput[0].toLowerCase();
        String taskDescription = (splitInput.length > 1) ? splitInput[1] : ""; // Check that desc is not empty

        try {
            switch (commandWord) {
            case CMD_EXIT:
                System.out.println("Bye! Remember come back visit Auntie hor.");
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

            case CMD_UNMARK:
                handleUnmark(taskDescription, tasks, ui, storage);
                break;

            case CMD_FIND:
                ArrayList<Task> foundTasks = tasks.findTasks(taskDescription);
                ui.printFoundTasks(foundTasks);
                break;

            case CMD_HELP:
                Ui.printHelp();
                break;

            default:
                // If no known commands used, clarify with user
                ui.printError("I dun understand you.");
                break;
            }
        } catch (Exception e) {
            ui.printError(e.getMessage());
            ui.printFormatHelp();
        }

        // Keep looping if false, since true=exitProgram
        return false;
    }


    /*
     * Section: handleTask functions to analyse task details and add to taskList
     */

     */
    private void handleTodo(String taskDesc, TaskList tasks, Ui ui, Storage storage)
            throws AuntieException, IOException {

        if (isEmptyDescription(taskDesc)) {
            throw new AuntieException("Wat you waaaant. Can specify onot?");
        }

        Task newTask = new Todo(taskDesc.trim());
        addTaskAndSave(tasks, ui, storage, newTask);
    }

    private void handleDeadline(String taskDesc, TaskList tasks, Ui ui, Storage storage)
            throws AuntieException, IOException {

        if (isEmptyDescription(taskDesc)) {
            throw new AuntieException("Aiyo, you want a deadline but no task name? Specify leh!");
        }

        // Expected format: <description> by <date/time>
        int byIndex = taskDesc.indexOf("by ");

        // Guard Clause: Ensure the "by" keyword exists and isn't at the very start
        if (byIndex <= 0) {
            throw new AuntieException("You forgot the 'by'! How Auntie know when the deadline?");
        }

        String deadlineName = taskDesc.substring(0, byIndex).trim();
        String by = taskDesc.substring(byIndex + 3).trim();

        if (deadlineName.isEmpty() || by.isEmpty()) {
            throw new AuntieException("Eh, you cannot leave the task or the time empty lah.");
        }

        Task t = new Deadline(deadlineName, by);
        addTaskAndSave(tasks, ui, storage, t);
    }

    private void handleEvent(String taskDesc, TaskList tasks, Ui ui, Storage storage)
            throws AuntieException, IOException {

        if (isEmptyDescription(taskDesc)) {
            throw new AuntieException("Event name where? Don't play play, tell Auntie what's happening.");
        }

        // Expected format: description from <startTime> to <endTime>
        int fromIndex = taskDesc.indexOf("from");
        int toIndex = taskDesc.indexOf("to");
        boolean isInvalidEvent = fromIndex == -1 || toIndex == -1 || fromIndex > toIndex;

        // Guard Clause: Check for existence and logical order
        if (isInvalidEvent) {
            throw new AuntieException("Your event format rabak. Use: <desc> from <start> to <end>");
        }

        String eventName = taskDesc.substring(0, fromIndex).trim();
        String from = taskDesc.substring(fromIndex + 4, toIndex).trim();
        String to = taskDesc.substring(toIndex + 2).trim();

        if (eventName.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new AuntieException("Auntie needs the event name, start time, AND end time hor.");
        }

        Task t = new Event(eventName, from, to);
        addTaskAndSave(tasks, ui, storage, t);
    }

    private void handleMark(String taskDesc, TaskList tasks, Ui ui, Storage storage)
            throws AuntieException, IOException {

        // Retrieve task to be marked
        int idx = parseIndex(taskDesc, tasks.getSize());
        Task t = tasks.getTask(idx);

        // Guard clause: ensure task is actually unmarked before marking
        if (t.isDone()) {
            ui.printError("Eh, u marked this liao. Can this task already lah.");
            return;
        }

        tasks.markTask(idx);
        storage.saveFile(tasks);
        ui.printMarkedTask(t);
    }

    private void handleUnmark(String taskDesc, TaskList tasks, Ui ui, Storage storage)
            throws AuntieException, IOException {

        // Retrieve task to be unmarked
        int idx = parseIndex(taskDesc, tasks.getSize());
        Task t = tasks.getTask(idx);

        // Guard clause: ensure task is actually marked before unmarking
        if (!t.isDone()) {
            ui.printError("Hoi, you unmarked already. Don't play play.");
            return;
        }

        tasks.unmarkTask(idx);
        storage.saveFile(tasks);
        ui.printUnmarkedTask(t);
    }

    private void handleDelete(String taskDesc, TaskList tasks, Ui ui, Storage storage)
            throws AuntieException, IOException {

        // Validate and convert input using our shared helper
        int taskIndex = parseIndex(taskDesc, tasks.getSize());

        // Execute deletion and retrieve the task for the announcement
        Task removedTask = tasks.deleteTask(taskIndex);

        // Save changes to local storage
        storage.saveFile(tasks);

        // Inform user of success
        ui.printDeletedTask(removedTask, tasks.getSize());
    }


    /*
     * Section: Helper functions
     */
    private static boolean isEmptyDescription(String desc) {
        return desc == null || desc.trim().isEmpty();
    }
    private int parseIndex(String input, int size) throws AuntieException {
        try {
            int idx = Integer.parseInt(input.trim()) - 1;
            if (idx < 0 || idx >= size) {
                throw new AuntieException("This task doesn't exist leh. You only got " + size + " tasks hor.");
            }
            return idx;
        } catch (NumberFormatException e) {
            throw new AuntieException("Eh, need number lah. Go see list again for task index.");
        }
    }

    private static void addTaskAndSave(TaskList tasks, Ui ui, Storage storage, Task t) throws IOException {
        tasks.addTask(t);
        ui.printAddedTask(t, tasks.getSize());
        storage.saveFile(tasks);
    }

    private static void printUpdatedTask(TaskList tasks, Storage storage, int idx) throws IOException {
        System.out.println(tasks.getTask(idx).toStringTaskIcons() + tasks.getTask(idx).getDescription());
        storage.saveFile(tasks);
    }
}