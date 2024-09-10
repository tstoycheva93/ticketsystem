package exceptions;

/**
 * The TicketException class is a custom exception that extends RuntimeException. 
 * It is used to signal issues related to tickets.
 */
public class TicketException extends RuntimeException {
    /**
     * Constructs a TicketException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public TicketException(String message) {
        super(message);
    }
}