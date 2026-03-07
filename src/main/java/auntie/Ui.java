package auntie;

import java.util.ArrayList;
import java.util.Scanner;

import auntie.task.Task;
import auntie.task.TaskList;

/**
 * Represents the user interface of the Auntie chatbot.
 * Handles all interactions with the user, including reading input commands
 * and displaying feedback with a specific personality.
 */
public class Ui {

    private final Scanner scanner;
    public static final String LOGO =
            "                      _   _      \n"
            + "     /\\              | | (_)     \n"
            + "    /  \\  _   _ _ __ | |_ _  ___ \n"
            + "   / /\\ \\| | | | '_ \\| __| |/ _ \\\n"
            + "  / ____ \\ |_| | | | | |_| |  __/\n"
            + " /_/    \\_\\____|_| |_|\\__|_|\\___|\n";

    /**
     * Initializes the UI by setting up the input scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message based on whether the user is new.
     * @param isNewUser True if no previous data was found, false otherwise.
     */
    public void printWelcome(boolean isNewUser) {
        if (!isNewUser) {
            System.out.println("Eh Auntie remember u! Come, I bring out ur task list for u.");
        } else {
            System.out.println("Oh first time meeting issit. I go find a notebook for you first ah.");
            System.out.println("If u unsure what to do, can ask for 'help' hor");
        }
        System.out.println("\nQuick, what u wan do?");
    }

    /**
     * Displays the chatbot's logo and initial greeting message.
     */
    public static void printGreeting() {
        Ui.printLine();
        System.out.println("Hallo! Can call me:");
        System.out.println(LOGO);
        System.out.println("Your life so rabak, Auntie help you keep track ok.");
        Ui.printLine();
    }

    /**
     * Displays an error message to the user.
     * @param errorMessage The error message to be displayed.
     */
    public void printError(String errorMessage) {
        System.out.println("Aiyo! " + errorMessage);
    }

    /**
     * Displays a confirmation message when a task is successfully added.
     * @param newTask The task that was added.
     * @param totalTasks The current total number of tasks in the list.
     */
    public void printAddedTask(Task newTask, int totalTasks) {
        System.out.println("Ok, added liao:");
        System.out.println("  " + newTask.toStringListFormat());
        System.out.println("Now u got " + totalTasks + " things to do hor.");
    }

    /** Same as {@link #printAddedTask}, but for task deletion. */
    public void printDeletedTask(Task deletedTask, int totalTasks) {
        System.out.println("Ok, removed liao:");
        System.out.println("  " + deletedTask.toStringListFormat());
        System.out.println("Now u got " + totalTasks + " more things to do hor.");
    }

    /**
     * Displays a confirmation message and the details of the marked task.
     * @param task The task that was successfully marked as done.
     */
    public void printMarkedTask(Task task) {
        System.out.println("Wah u finally stopped lazing around. Good good.");
        System.out.println("  " + task.toStringListFormat());
    }

    /** Same as {@link #printMarkedTask}, but for unmarking a task. */
    public void printUnmarkedTask(Task task) {
        System.out.println("Wah you never finish then you mark as done? Can, unmarked liao.");
        System.out.println("  " + task.toStringListFormat());
    }

    /**
     * Displays all tasks currently in the task list.
     * @param tasks The TaskList object containing user tasks.
     */
    public void printList(TaskList tasks) {
        // Handle case: Empty list
        if (tasks.isEmpty()) {
            System.out.println("You very free hor. Nothing to do.");
            return;
        }

        // Handle case: Non-empty list
        System.out.println("Aiyooo, look at all these tasks. Better get ur bum moving.");
        for (int i = 0; i < tasks.getSize(); i++) {
            printIndexedListItem(tasks, i);
        }
    }

    /**
     * Displays the results of a keyword search operation.
     * @param results The list of tasks that matched the search keyword.
     */
    public void printFoundTasks(ArrayList<Task> results) {
        if (results.isEmpty()) {
            System.out.println("I look until my eyes pain also cannot find leh");
            return;
        }
        System.out.println("Okok, I find for you ah:");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + "." + results.get(i).toStringListFormat());
        }
    }

    /*
     * Section: Helper functions
     */
    private static void printIndexedListItem(TaskList tasks, int i) {
        System.out.println((i + 1) + ". " + tasks.getTask(i).toStringListFormat());
    }

    public static void printLine() {
        System.out.println("-*-");
    }

    /*
     * Section: Longer functions to explain and help with unfamiliar user's experience
     */
    public void printFormatHelp() {
        System.out.println("Your format wrong leh. Listen properly ah:");
        System.out.println("1. **todo** <description>");
        System.out.println("   e.g., todo buy kopi");
        System.out.println("2. **deadline** <description> **by** <date/time>");
        System.out.println("   e.g., deadline CS2113 tutorial **by** Friday 11pm");
        System.out.println("3. **event** <description> **from** <start> **to** <end>");
        System.out.println("   e.g., event Water Polo training **from** 7pm **to** 9pm");
        System.out.println("\nUnderstand onot? Try again");
    }

    public static void printHelp() {
        String helpMessage = """
        Mai kanchiong, Auntie will help you.
        Here is what Auntie can do for you:
        
        **VIEWING TASKS**
          list           - See ur whole task list.
          find <keyword> - Show u the tasks containing ur keyword.
        
        **ADDING TASKS**
          todo <desc>                         - General task to do.
          deadline <desc> by <time>           - Task with a due date.
          event <desc> from <start> to <end>  - Task with a duration.
        
        **MANAGING TASKS**
          mark <index>   - Finished liao? Mark the task. Shiok hor.
          unmark <index> - Not finished yet but you cockeye and mark as done? Uncheck task.
          delete <index> - Clear task from ur list! One down lio.
        
        **OTHERS**
          bye           - Close the program (Auntie will save ur data!).
          help          - See this message again. Buay paiseh hor.
        --------------------------------------------------
        Just type what u need and Auntie will help u!
        """;

        System.out.println(helpMessage);
    }
}