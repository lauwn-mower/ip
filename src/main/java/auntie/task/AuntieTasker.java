package auntie.task;// This class is a task manager to manage the Task class and its subclasses

import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

/* Class Usage details
 * This class is a TaskManager that continuously scans for user inputs, decodes those inputs and carries out commands respectively
 */

public class AuntieTasker {

    // Constant inputs
    public static final String CMD_LIST = "list";
    public static final String CMD_EXIT = "bye";
    public static final String CMD_MARK = "mark";
    public static final String CMD_UNMARK = "unmark";
    public static final String CMD_TODO = "todo";
    public static final String CMD_DEADLINE = "deadline";
    public static final String CMD_EVENT = "event";
    public static final String CMD_DELETE = "delete";

    // Task Class members to track all tasks
    public static ArrayList<Task> taskList = new ArrayList<>();
    private static int taskCount = 0;

    // Flag to exit program
    public static boolean exitProgram = false;

    /* Level-1 Task
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
    */

    /*
     * Section of methods dealing with bot responses when adding, deleting or viewing tasks
     */
    public static void addedTaskConfirmation(int taskNumber){
        System.out.println("Ok, added liao:");
        System.out.println(taskList.get(taskNumber).toStringListFormat());

        System.out.println("Now u got " + (taskCount + 1) + " things to do hor.");
    }

    public static void removedTaskConfirmation(String removedTask){
        System.out.println("Ok, removed liao:");
        System.out.println(removedTask);
        System.out.println("\nNow u got " + (taskCount + 1) + " more things to do hor.");
        printList();
    }

    // This method goes through all the tasks in taskList and prints them out with isDone status
    public static void printList(){
        // Empty list's response
        if (taskCount == 0) {
            System.out.println("You very free hor. Nothing to do");
            return;
        }

        // Non-empty list's response and printing loop
        System.out.println("Aiyooo, look at all these tasks. Better get ur bum moving.");
        for (int i = 0; i < taskCount; i += 1){
            System.out.println( (i+1) + ". " + taskList.get(i).toStringListFormat());
        }
    }

    /*
     * Section of methods dealing with decoding user input and executing commands
     */

    // This method identifies what to do based on userInput
    public static void decodeCommand(String userInput) {

        // First analyse the firstWord to check if list/bye or Task+details
        String[] splitInput = userInput.split(" ", 2);
        String firstWord = splitInput[0];
        String taskDesc = (splitInput.length > 1) ? splitInput[1] : "";

        try {
            switch (firstWord) {

            // Print list of tasks
            case CMD_LIST: {
                printList();
                break;
            }

            // Exit program
            case CMD_EXIT: {
                System.out.println("U finally done ah? Ok stop bothering me now. Shooshoo.");
                exitProgram = true;
                break;
            }

            // Set specified task to isDone=true
            case CMD_MARK: {
                int taskNumber = parseInt(splitInput[1]) - 1; // Array starts at 0 but user reads from 1
                taskList.get(taskNumber).setDone(true);
                System.out.println("Wah u finally stopped lazing around. Good good");
                System.out.println(taskList.get(taskNumber).toStringTaskIcons() + taskList.get(taskNumber).description);
                break;
            }

            // Set specified task to isDone=false
            case CMD_UNMARK: {
                int taskNumber = parseInt(taskDesc) - 1; // Array starts at 0 but user reads from 1
                taskList.get(taskNumber).setDone(false);
                System.out.println("U lie to me issit? Want cheat horrr. U better watch out");
                System.out.println(taskList.get(taskNumber).toStringTaskIcons() + taskList.get(taskNumber).description);
                break;
            }

            // Remove a task from taskList
            case CMD_DELETE: {
                int taskNumber = parseInt(taskDesc) - 1; // Array starts at 0 but user reads from 1
                String removedTask = taskList.get(taskNumber).toStringListFormat();
                taskList.remove(taskNumber);
                taskCount -= 1;

                removedTaskConfirmation(removedTask);
                break;
            }

            /*
             * Tasks classified into Todo, Deadline, Event
             * Handled by respective handle<Task>() methods:
             *     If new task added: Construct and add to taskList by type
             *     Confirm with user that the task has been added
             *     Increment taskCount
             */
            case CMD_TODO: {
                addTodo(splitInput, taskDesc);
                break;
            }

            case CMD_DEADLINE: {
                addDeadline(taskDesc);
                break;
            }

            case CMD_EVENT: {
                addEvent(taskDesc);
                break;
            }

            // If not a command, clarify
            default: {
                System.out.println("I dun understand you. Follow format pls.");
                break;
            }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Huh? Wat you waaaant. Can specify onot?");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Aiya formatting wrong lah. Do again" );
        } catch (AuntieException e){
            // Catch when there's an empty taskDesc after the command, handled in handleTodo()
            System.out.println(e.getMessage());
        }
    }

    private static void addEvent(String taskDesc) {
        // Split userInput line by its description and deadline, then construct Deadline
        String[] splitComponents = taskDesc.split("/", 3);
        String eventName = splitComponents[0];
        String eventDateFrom = splitComponents[1];
        String eventDateTo = splitComponents[2];

        // Add task to taskList
        Event newEvent = new Event(eventName, eventDateFrom, eventDateTo);
        taskList.add(taskCount, newEvent);
        addedTaskConfirmation(taskCount);
        taskCount += 1;
    }

    // handleTask() methods
    private static void addDeadline(String taskDesc) {
        // Split userInput line by its description and deadline, then construct Deadline
        String[] splitComponents = taskDesc.split("/", 2);
        String deadlineName = splitComponents[0];
        String deadlineBy = splitComponents[1];

        // Add new task to taskList
        Deadline newDeadline = new Deadline(deadlineName, deadlineBy);
        taskList.add(taskCount, newDeadline);
        addedTaskConfirmation(taskCount);
        taskCount += 1;
    }

    private static void addTodo(String[] splitInput, String taskDesc) throws AuntieException {
        // User might enter "todo" without a task -> program crash
        if (splitInput.length < 2){
            throw new IndexOutOfBoundsException();
        }

        if (taskDesc.trim().isEmpty()){
            throw new AuntieException("U blind issit. Got nothing in this todo");
        }

        // Add new task to taskList
        Todo newTodo = new Todo(taskDesc);
        taskList.add(taskCount, newTodo);
        addedTaskConfirmation(taskCount);
        taskCount += 1;
    }


    public static void main(String[] args){
        /* Procedures
         * 1. Bot greets user
         * 2. Bot ascertains if user is new or existing (local file exists)
         * 3. Load / Create taskList
         * 4. Start task managing proper
         * 5. Save file before exiting program
         */
        auntie.startup.AuntieGreeting.greetUser();
        auntie.startup.AuntieGreeting.setUserNickname();

        auntie.startup.AuntieRetrieveFile();
        // TODO: account for first-time vs local user

        System.out.println("\nQuick, what you want do?");

        Scanner in = new Scanner(System.in);
        while (!exitProgram){
            String userInput = in.nextLine();
            decodeCommand(userInput);
        }
    }
}
