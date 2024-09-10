package exceptions;

/**
 * The EventException class is a custom exception that extends RuntimeException.
 * It is used to signal issues related to events.
 */
public class EventException extends RuntimeException {
    /**
     * Constructs an EventException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public EventException(String message) {
        super(message);
    }
}
