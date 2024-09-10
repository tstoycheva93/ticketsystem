package exceptions;

/**
 * The FileException class is a custom exception that extends RuntimeException.
 * It is used to signal issues related to file operations.
 */
public class FileException extends RuntimeException {
    /**
     * Constructs a FileException with the specified detail message.
     *
     * @param message The detail message explaining the cause of the exception.
     */
    public FileException(String message) {
        super(message);
    }
}