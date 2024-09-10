package menu.commands;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

/**
 * The BookSeatCommand class implements the Commands interface.
 * It is responsible for booking a seat using the specified Operation instance.
 */
public class BookSeatCommand implements Commands {
    private final Operation operation;

    /**
     * Constructs a BookSeatCommand with the specified Operation.
     *
     * @param operation The operation to be executed.
     */
    public BookSeatCommand(Operation operation) {
        this.operation = operation;
    }

    /**
     * Executes the book seat operation using the provided EventBooker, Halls, and data.
     *
     * @param eventBooker The EventBooker instance.
     * @param halls       The Halls instance.
     * @param data        The data required for the operation.
     */
    @Override
    public void execute(EventBooker eventBooker, Halls halls, String[] data) {
        operation.process(eventBooker, halls, data);
    }
}