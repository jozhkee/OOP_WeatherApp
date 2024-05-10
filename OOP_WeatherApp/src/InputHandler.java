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

    public int getOption() throws CustomException {
        // TODO: Somehow we need to make this look better
        System.out.print(
                "Please enter a number corresponding to information you want to retrieve:\n" +
                        "0. Exit 1. Show advanced information 2. Show wind speed 3. Show pressure " +
                        "4. Show Humidity\n5. Show precipitation 6. Show Cloud cover percentage " +
                        "7. Show \"Feels like\" Temperature 8. Show visibility\n9. Show UV index 10. Show wind gust 11. Show wind direction 12. Next city\n");

        int option = -1;
        while (option == -1) {
            option = scanner.nextInt();
            if (option > 12 || option < 0) {
                option = -1;
                System.out.println("Please enter a valid number.");
            }

        }
        scanner.nextLine();
        return option;

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
