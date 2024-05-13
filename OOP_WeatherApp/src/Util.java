/**
 * The Util class provides utility methods for various operations.
 */

public class Util {
    /**
     * Checks if the input string contains any digits.
     *
     * @param input The input string to be checked.
     * @return true if the input string contains digits, false otherwise.
     */

    public boolean containsDigits(String input) {
        return input.matches(".*\\d.*");
    }

}
