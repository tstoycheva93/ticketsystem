package menu.commands;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

/**
 * The AddEventCommand class implements the Commands interface.
 * It is responsible for adding a new event using the specified Operation instance.
 */
public class AddEventCommand implements Commands {
    private final Operation operation;

    /**
     * Constructs an AddEventCommand with the specified Operation.
     *
     * @param operation The operation to be executed.
     */
    public AddEventCommand(Operation operation) {
        this.operation = operation;
    }

    /**
     * Executes the add event operation using the provided EventBooker, Halls, and data.
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