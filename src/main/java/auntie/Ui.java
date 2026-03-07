package auntie;

import java.util.ArrayList;
import java.util.Scanner;

import auntie.task.Task;
import auntie.task.TaskList;

public class Ui {

    public static final String LOGO =
            "                      _   _      \n"
            + "     /\\              | | (_)     \n"
            + "    /  \\  _   _ _ __ | |_ _  ___ \n"
            + "   / /\\ \\| | | | '_ \\| __| |/ _ \\\n"
            + "  / ____ \\ |_| | | | | |_| |  __/\n"
            + " /_/    \\_\\____|_| |_|\\__|_|\\___|\n";

    // Create a Scanner once and use across methods instead of creating a scanner for each method
    private final Scanner scanner;

    // Construct UI class to scan and process inputs accordingly
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printWelcome(boolean isNewUser) {
        if (!isNewUser) {
            System.out.println("Eh Auntie remember u! Come, I bring out ur task list for u.");
        } else {
            System.out.println("Oh first time meeting issit. I go find a notebook for you first ah.");
            System.out.println("If you unsure what do do, can ask for 'help' hor");
        }
        System.out.println("\nQuick, what you want do?");
    }

    // This method prints the Chatbot's self-introduction and purpose
    public static void printGreeting() {
        Ui.printLine();
        System.out.println("Hallo! Can call me:");
        System.out.println(LOGO);
        System.out.println("Your life so rabak, Auntie help you keep track ok.");
        Ui.printLine();
    }

    public void printError(String errorMessage) {
        System.out.println("Aiyo! " + errorMessage);
    }

    public void printAddedTask(Task newTask, int totalTasks) {
        System.out.println("Ok, added liao:");
        System.out.println("  " + newTask.toStringListFormat());
        System.out.println("Now u got " + totalTasks + " things to do hor.");
    }

    public void printDeletedTask(Task deletedTask, int totalTasks) {
        System.out.println("Ok, removed liao:");
        System.out.println("  " + deletedTask.toStringListFormat());
        System.out.println("Now u got " + totalTasks + " more things to do hor.");
    }

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
        System.out.println("Mai kanchiong, Auntie will help you.");
        System.out.println("Here is what Auntie can do for you:");
        System.out.println("");
        System.out.println("**VIEWING TASKS**");
        System.out.println("  list           - See ur whole task list.");
        System.out.println("  find <keyword> - Show u the tasks containing ur keyword.");
        System.out.println("");
        System.out.println("**ADDING TASKS**");
        System.out.println("  todo <desc>                         - General task to do.");
        System.out.println("  deadline <desc> by <time>           - Task with a due date.");
        System.out.println("  event <desc> from <start> to <end>  - Task with a duration.");
        System.out.println("");
        System.out.println("**MANAGING TASKS**");
        System.out.println("  mark <index>   - Finished liao? Mark the task. Shiok hor.");
        System.out.println("  unmark <index> - Not finished yet but you cockeye and mark as done? Uncheck task.");
        System.out.println("  delete <index> - Clear task from ur list! One down lio.");
        System.out.println("");
        System.out.println("**OTHERS**");
        System.out.println("  bye           - Close the program (Auntie will save ur data!).");
        System.out.println("  help          - See this message again. Buay paiseh hor.");
        System.out.println("--------------------------------------------------");
        System.out.println("Just type what u need and Auntie will help u!");
    }
}