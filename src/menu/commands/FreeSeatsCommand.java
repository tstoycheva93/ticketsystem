package menu.commands;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

/**
 * The FreeSeatsCommand class implements the Commands interface.
 * It is responsible for executing an operation to check free seats using the specified Operation instance.
 */
public class FreeSeatsCommand implements Commands {
    private final Operation operation;

    /**
     * Constructs a FreeSeatsCommand with the specified Operation.
     *
     * @param operation The operation to be executed.
     */
    public FreeSeatsCommand(Operation operation) {
        this.operation = operation;
    }

    /**
     * Executes the free seats operation using the provided EventBooker, Halls, and data.
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