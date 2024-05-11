import java.util.InputMismatchException;
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

        int option = -1;
        while (option == -1) {
            try {
                option = scanner.nextInt();
                if (option > 12 || option < 0) {
                    option = -1;
                    System.out.println("Please enter a valid number.");
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Please enter a valid number.");
                option = -1;
            }
        }
        scanner.nextLine();
        return option;
    }

    public void printOptionTable() {
        String[] firstColumn = { "| 0. Exit", "| 1. Show advanced information", "| 2. Show wind speed",
                "| 3. Show pressure", "| 4. Show Humidity", "| 5. Show precipitation",
                "| 6. Show Cloud cover percentage" };
        String[] secondColumn = { "7. Show \"Feels like\" Temperature", "8. Show visibility", "9. Show UV index",
                "10. Show wind gust", "11. Show wind direction", "12. Next city", "" };
        String[] thirdColumn = { "|", "|", "|", "|", "|", "|", "|" };

        int firstColumnWidth = 40;
        int secondColumnWidth = 34;
        int thirdColumnWidth = 20;

        System.out.println("-".repeat(firstColumnWidth + (secondColumnWidth + 1)));

        for (int i = 0; i < firstColumn.length; i++) {

            String formattedFirst = String.format("%-" + firstColumnWidth + "s", firstColumn[i]);
            String formattedSecond = String.format("%-" + secondColumnWidth + "s", secondColumn[i]);
            String formattedThird = String.format("%-" + thirdColumnWidth + "s", thirdColumn[i]);
            System.out.println(formattedFirst + formattedSecond + formattedThird);
        }

        System.out.println("-".repeat(firstColumnWidth + (secondColumnWidth + 1)));

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
