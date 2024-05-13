/**
 * CustomException is a subclass of Exception used to represent custom
 * exceptions in the application.
 */

public class CustomException extends Exception {

    /**
     * Constructs a new CustomException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the
     *                getMessage() method).
     */
    public CustomException(String message) {
        super(message);
    }
}
