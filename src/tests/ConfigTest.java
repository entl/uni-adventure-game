package tests;

public class ConfigTest {

    // ansi color codes for the console
    // it is used for better visualization of the test results
    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void outputResult(boolean result, String testName) {
        if (result) {
            System.out.println(ANSI_GREEN + "- " + testName + " passed" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "- " + testName + " failed" + ANSI_RESET);
        }
    }
}
