package auntie.task;

/**
 * Represents exceptions specific to the Auntie chatbot application.
 * Used to signal errors in user input formatting, task indexing, or
 * file persistence issues with a personalized message.
 */
public class AuntieException extends Exception {

    /**
     * Initializes a new AuntieException with a specific error message.
     * @param message The "Auntie-style" scolding to be displayed to the user.
     */
    public AuntieException(String message) {
        super(message);
    }
}
