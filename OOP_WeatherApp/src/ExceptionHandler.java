import java.io.IOException;

/**
 * The ExceptionHandler class provides a static method for handling various
 * exceptions that may occur in the application.
 */
public class ExceptionHandler {

    /**
     * Handles the specified exception by printing appropriate error messages.
     *
     * @param e The exception to be handled.
     */
    public static void handleException(Exception e) {

        if (e.getMessage() == "Empty Input is not allowed!") {
            System.out.println(e.getMessage());
        } else if (e.getMessage() == "Invalid city name!") {
            System.out.println(e.getMessage());
        } else if (e instanceof IOException || e.getMessage() == "Invalid Country") {
            System.out.println("Provided city name could not be found!");
        } else if (e.getMessage() == "EXIT") {
            System.out.println("Hope to see you soon!");
            System.exit(0);
        } else {
            System.out.println("Unknown exception occured : " + e.getStackTrace());
            System.exit(1);
        }

    }
}
