package exceptions;

/**
 * The `ThereIsNoSuchSeat` class is a custom runtime exception that is thrown when a requested seat does not exist.
 */
public class ThereIsNoSuchSeat extends RuntimeException {

    /**
     * Constructs a new `ThereIsNoSuchSeat` exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public ThereIsNoSuchSeat(String message) {
        super(message);
    }
}
