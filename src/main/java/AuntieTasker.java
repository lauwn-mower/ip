import java.util.Scanner;

public class AuntieTasker {

    public static String[] taskList = new String[100];
    public static int taskCount = 0;

    public static void botAddList(){
        Scanner input = new Scanner(System.in);

        // Keep scanning for inputs unless program exited
        while (true){
            String userInput = input.nextLine().trim();

            // Exit program if commanded
            if (userInput.equalsIgnoreCase("bye")){
                System.out.println("U finally done ah? Ok stop bothering me now. Shooshoo." );
                break;
            }

            // Print list if commanded
            if (userInput.equalsIgnoreCase("list")){
                System.out.println("Aiyooo, look at all these tasks. Better get ur bum moving.");
                for (int i = 0; i < taskCount; i += 1){
                    System.out.println((i+1) + ". " + taskList[i]);
                }
            } else {
            // If command is neither "list" nor "bye", all inputs are new tasks
                taskList[taskCount] = userInput;
                taskCount += 1;
                System.out.println("Added liao: " + userInput);
            }
        }
    }

    // This method scans for user inputs and echoes back
    public static void botEcho(){
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

    public static void main(String[] args){
        AuntieGreeting.greetUser();

        System.out.println("");
        System.out.println("Quick, what you want do?");

        botAddList();
    }
}
