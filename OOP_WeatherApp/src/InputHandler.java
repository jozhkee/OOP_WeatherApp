import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getCity() throws CustomException {
        System.out.print("Enter the city name: ");
        String city = scanner.nextLine();
        Util util = new Util();
        if (checkForExit(city)) {
            throw new CustomException("EXIT");
        }
        if (city == "") {
            throw new CustomException("Empty Input is not allowed!");
        }
        if (util.containsDigits(city)) {
            throw new CustomException("Invalid city name!");
        }
        return city;
    }

    public boolean checkForExit(String input) {
        if (input.trim().equalsIgnoreCase("exit")) {
            return true;
        } else {
            return false;
        }

    }

    public void close() {
        scanner.close();
    }
}
