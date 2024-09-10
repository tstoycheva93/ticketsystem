package menu.baseCommands;

import entities.EventBooker;
import entities.Halls;
import menu.commands.Commands;
import operations.interfaces.Operation;

/**
 * The CloseCommand class implements the Commands interface.
 * It is responsible for executing a close operation, which is defined by the given Operation instance.
 */
public class CloseCommand implements Commands {
    private final Operation operation;

    /**
     * Constructs a CloseCommand with the specified Operation.
     *
     * @param operation The operation to be executed.
     */
    public CloseCommand(Operation operation) {
        this.operation = operation;
    }

    /**
     * Executes the close operation using the provided EventBooker, Halls, and data.
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