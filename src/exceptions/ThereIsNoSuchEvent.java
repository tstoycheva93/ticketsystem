package exceptions;

/**
 * The `ThereIsNoSuchEvent` class is a custom runtime exception that is thrown when a requested event does not exist.
 */
public class ThereIsNoSuchEvent extends RuntimeException {

    /**
     * Constructs a new `ThereIsNoSuchEvent` exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public ThereIsNoSuchEvent(String message) {
        super(message);
    }
}
