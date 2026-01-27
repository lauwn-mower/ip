import java.util.Scanner;

public class AuntieTasker {

    public static void main(String[] args){
        AuntieGreeting.greetUser();

        System.out.println("");
        System.out.println("Quick, what you want do?");

        // Implement Echo function
        String userInput;
        Scanner input = new Scanner(System.in);
        userInput = input.nextLine();

        while (!userInput.equals("bye")){
            // Bot continues to take in inputs and echo if input!="bye"
            System.out.println(userInput);
            userInput = input.nextLine();
        }

        System.out.println("U finally done ah? Ok stop bothering me, shooshoo");
    }
}
