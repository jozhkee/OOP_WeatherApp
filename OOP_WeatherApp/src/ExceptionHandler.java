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
    public static String handleException(Exception e) {

        if (e.getMessage() == "Empty Input is not allowed!") {
            return e.getMessage();
        } else if (e.getMessage() == "Invalid city name!") {
            return e.getMessage();
        } else if (e instanceof IOException || e.getMessage() == "Invalid Country") {
            return "Provided city name could not be found!";
        } else {
            return "Unknown exception occured : " + e.getStackTrace();
        }

    }
}
