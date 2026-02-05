// This class is a task manager to manage the Task class and its subclasses

import java.util.Scanner;
import static java.lang.Integer.parseInt;

/* Class Usage details
 * co-use with Task class
 * AuntieTasker.echo() (remove from main()), Scan for inputs
 * This class is a TaskManager that continuously scans for user inputs, decodes those inputs and carries out commands respectively
 */

public class AuntieTasker {

    // Task Class members to track all tasks
    public static final int sizeOfTaskList = 100;
    public static Task[] taskList = new Task[sizeOfTaskList];
    public static int taskCount = 0;

    // Flag to exit program
    public static boolean exitProgram = false;

    // This method scans for user inputs and echoes back
    public static void echo(){
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();

        while (!userInput.equals("bye")){
            // Bot continues to take in inputs and echo if input!="bye"
            System.out.println(userInput);
            userInput = input.nextLine();
        }
        System.out.println("U finally done ah? Ok stop bothering me, shooshoo.");
    }

    public static void addedTaskConfirmation(int taskNum){
        System.out.println("Ok, added liao:");
        System.out.println(taskList[taskNum].toStringListFormat());
    }

    // This method goes through all the tasks in taskList and prints them out with isDone status
    public static void printList(){
        System.out.println("Aiyooo, look at all these tasks. Better get ur bum moving.");
        for (int i = 0; i < taskCount; i += 1){
            System.out.println( (i+1) + ". " + taskList[i].toStringListFormat());
        }
    }


    // This method identifies what to do based on userInput
    public static void decodeCommand(String userInput){

        // First analyse the firstWord to check if list/bye or Task+details
        String[] splitInput = userInput.split(" ", 2);
        String firstWord = splitInput[0];

        switch (firstWord) {

        // Print list of tasks
        case "list": {
            printList();
            break;
        }

        // Exit program
        case "bye": {
            System.out.println("U finally done ah? Ok stop bothering me now. Shooshoo.");
            exitProgram = true;
            break;
        }

        // Set specified task to isDone=true
        case "mark": {
            int taskNumber = parseInt(splitInput[1]) - 1; // Array starts at 0 but user reads from 1
            taskList[taskNumber].setDone(true);
            System.out.println("Wah u finally stopped lazing around. Good good");
            System.out.println(taskList[taskNumber].toStringTaskIcons() + taskList[taskNumber].description);
            break;
        }

        // Set specified task to isDone=false
        case "unmark": {
            int taskNumber = parseInt(splitInput[1]) - 1; // Array starts at 0 but user reads from 1
            taskList[taskNumber].setDone(false);
            System.out.println("U lie to me issit? Want cheat horrr. U better watch out");
            System.out.println(taskList[taskNumber].toStringTaskIcons() + taskList[taskNumber].description);
            break;
        }

        /*
         * Tasks classified into Todo, Deadline, Event
         * If new task added: Construct and add to taskList by type
         * Confirm with user that the task has been added
         * Increment taskCount
         */
        case "todo": {

            // Add new task to taskList
            String taskDesc = splitInput[1];
            taskList[taskCount] = new Todo(taskDesc);
            addedTaskConfirmation(taskCount);
            taskCount += 1;
            break;
        }

        case "deadline": {
            // Split userInput line by its description and deadline, then construct Deadline
            String[] splitComponents = splitInput[1].split("/", 2);
            String taskDesc = splitComponents[0];
            String dateBy = splitComponents[1];

            // Add new task to taskList
            taskList[taskCount] = new Deadline(taskDesc, dateBy);
            addedTaskConfirmation(taskCount);
            taskCount += 1;
            break;
        }

        case "event": {
            // Split userInput line by its description and deadline, then construct Deadline
            String[] splitComponents = splitInput[1].split("/", 3);
            String taskDesc = splitComponents[0];
            String dateFrom = splitComponents[1];
            String dateTo = splitComponents[2];

            // Add task to taskList
            taskList[taskCount] = new Event(taskDesc, dateFrom, dateTo);
            addedTaskConfirmation(taskCount);
            taskCount += 1;
            break;
        }

        // If not a command, clarify
        default: {
            System.out.println("I dun understand you. Follow format pls.");
            break;
        }
        }
    }


    public static void main(String[] args){
        AuntieGreeting.greetUser();
        AuntieGreeting.setUserNickname();

        System.out.println("\nQuick, what you want do?");

        Scanner newLine = new Scanner(System.in);
        while (!exitProgram){
            String userInput = newLine.nextLine();
            decodeCommand(userInput);
        }
    }
}
