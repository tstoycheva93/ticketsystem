import menu.invoker.Invoker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The entry point of the application.
 * <p>
 * This class contains the main method that initializes the {@code Invoker} and starts the command processing.
 * </p>
 */
public class Main {
    /**
     * The main method that serves as the entry point of the application.
     * <p>
     * It creates an instance of {@code Invoker} and invokes it to start the command processing.
     * </p>
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.invoke();
    }
}

