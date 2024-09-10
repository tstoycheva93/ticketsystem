package menu.baseCommands;

import entities.EventBooker;
import entities.Halls;
import menu.commands.Commands;
import operations.interfaces.Operation;

/**
 * The SaveCommand class implements the Commands interface.
 * It is responsible for executing a save operation, which is defined by the given Operation instance.
 */
public class SaveCommand implements Commands {
    private final Operation operation;

    /**
     * Constructs a SaveCommand with the specified Operation.
     *
     * @param operation The operation to be executed.
     */
    public SaveCommand(Operation operation) {
        this.operation = operation;
    }

    /**
     * Executes the save operation using the provided EventBooker, Halls, and data.
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