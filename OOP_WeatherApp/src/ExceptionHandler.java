import java.io.IOException;

public class ExceptionHandler {

    public static void handleException(Exception e) {

        if (e.getMessage() == "Empty Input is not allowed!") {
            System.out.println(e.getMessage());
        } else if (e.getMessage() == "Invalid city name!") {
            System.out.println(e.getMessage());
        } else if (e instanceof IOException) {
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
