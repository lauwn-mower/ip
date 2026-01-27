import java.util.Scanner;

public class AuntieGreeting {

    // This method prints the Chatbot's self-introduction and purpose
    public static void greetUser(){

        String logo = 	      "                      _   _      \n"
                + "     /\\              | | (_)     \n"
                + "    /  \\  _   _ _ __ | |_ _  ___ \n"
                + "   / /\\ \\| | | | '_ \\| __| |/ _ \\\n"
                + "  / ____ \\ |_| | | | | |_| |  __/\n"
                + " /_/    \\_\\____|_| |_|\\__|_|\\___|\n";

        System.out.println("Hallo! You can call me:");
        System.out.println(logo);

        System.out.println("Your life so rabak, Auntie help you keep track ok.");
        System.out.println(" ");
    }

    // This method personalises how the Chatbot will address the user, in line with the Chatbot's personality
    public static void setUserNickname(){
        System.out.println("Actually, you what gender ah?");
        System.out.println("  > Enter your gender: (Male) / (Female)");

        Scanner input = new Scanner(System.in);
        String user_gender;
        user_gender = input.nextLine().toUpperCase();
        String user_nickname;

        if (user_gender.equals("MALE")){
            user_nickname = "Ah Boy";
        } else if (user_gender.equals("FEMALE")) {
            user_nickname = "Ah Girl";
        } else {
            user_nickname = "Ah Boy";
            System.out.println("Aiyo you type wrong issit. Nevermind ah I just call you Ah Boy.");
        }

        System.out.println("Ok! I call you " + user_nickname + " hor.");
    }

    public static void main(String[] args){

        greetUser();
        setUserNickname();

        // Prompt user for input commands and task details
        System.out.println("Quick, what you want do?");
    }
}
