package auntie.startup;

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

        System.out.println("Hallo! Can call me:");
        System.out.println(logo);

        System.out.println("Your life so rabak, Auntie help you keep track ok.");
        System.out.println(" ");
    }

    // This method personalises how the Chatbot will address the user, in line with the Chatbot's personality
    public static void setUserNickname(){
        // Prompt user to select their gender
        System.out.println("Actually, you what gender ah?");
        System.out.println("  > Enter your gender: (Male) / (Female)");

        // Scan in user input
        Scanner input = new Scanner(System.in);
        String userGender = input.nextLine().toUpperCase();
        String userNickname;

        // Based on option chosen by user, Chatbot will address user accordingly
        if (userGender.equals("MALE")){
            userNickname = "Ah Boy";
        } else if (userGender.equals("FEMALE")) {
            userNickname = "Ah Girl";
        } else {
            userNickname = "Ah Boy";
            System.out.println("Aiyo you type wrong issit. Nevermind ah I just call you Ah Boy.");
        }

        System.out.println("Ok! I call you " + userNickname + " hor.");
    }

    public static void main(String[] args){
        greetUser();
        setUserNickname();

        // Prompt user for input commands and task details
        System.out.println("Quick, what you want do?");
    }
}
