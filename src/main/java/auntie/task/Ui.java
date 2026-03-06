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

    //adapt from auntiegreeting
    public void printWelcome(boolean isNewUser) {
        if (!isNewUser) {
            System.out.println("Eh Auntie remember u! Come, I bring out ur task list for u.");
        } else {
            System.out.println("Oh first time meeting issit. I go find a notebook for you first ah.");
        }
        System.out.println("\nQuick, what you want do?");
    }

  //TODO: add printing functions

    public void printList(TaskList taskList) {
        //TODO
    }

    public void printAddedTask(TaskList taskList) {
        //TODO
    }

    public void printDeletedTask(TaskList taskList) {
        //TODO
    }

}