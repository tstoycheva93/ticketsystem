package exceptions;

/**
 * The `ThereIsNoSuchHall` class is a custom runtime exception that is thrown when a requested hall does not exist.
 */
public class ThereIsNoSuchHall extends RuntimeException {

    /**
     * Constructs a new `ThereIsNoSuchHall` exception with the specified detail message.
     *
     * @param message The detail message.
     */
    public ThereIsNoSuchHall(String message) {
        super(message);
    }
}
