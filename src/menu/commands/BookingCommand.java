package menu.commands;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

/**
 * The BookingCommand class implements the Commands interface.
 * It is responsible for executing a booking operation using the specified Operation instance.
 */
public class BookingCommand implements Commands {
    private final Operation operation;

    /**
     * Constructs a BookingCommand with the specified Operation.
     *
     * @param operation The operation to be executed.
     */
    public BookingCommand(Operation operation) {
        this.operation = operation;
    }

    /**
     * Executes the booking operation using the provided EventBooker, Halls, and data.
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