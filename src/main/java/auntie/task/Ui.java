package auntie.task;

import java.util.Scanner;

public class Ui {
    
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
        }
        System.out.println("\nQuick, what you want do?");
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

    /*
     * Section: Helper functions
     */
    private static void printIndexedListItem(TaskList tasks, int i) {
        System.out.println((i + 1) + ". " + tasks.getTask(i).toStringListFormat());
    }

    public void printLine() {
        System.out.println(" ");
        System.out.println("-*-");
        System.out.println(" ");
    }
}