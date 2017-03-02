package data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * Send an informational message to the console using a formatted string.
     *
     * @param message Message to send to the console.
     * @param args    Objects to include in the formatted string.
     */
    public static void info(String message, Object... args) {
        write(String.format(message, args), 'I');
    }

    /**
     * Send an error message to the console using a formatted string.
     *
     * @param message Message to send to the console.
     * @param args    Objects to include in the formatted string.
     */
    public static void error(String message, Object... args) {
        write(String.format(message, args), 'E');
    }

    /**
     * Send a message to the console.
     *
     * @param message       Message to send to the console.
     * @param typeIndicator Message type indicator to send to the console.
     */
    private static void write(String message, char typeIndicator) {
        Date currentDate = new Date();
        message = String.format("[%s/%s] %s", timeFormat.format(currentDate), typeIndicator, message);
        System.out.println(message);
    }
}
