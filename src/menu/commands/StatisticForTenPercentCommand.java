
package menu.commands;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

/**
 * The StatisticForTenPercentCommand class implements the Commands interface.
 * It is responsible for executing an operation to calculate statistics for 10% of the event attendees.
 */
public class StatisticForTenPercentCommand implements Commands {
    private final Operation operation;

    /**
     * Constructs a StatisticForTenPercentCommand with the specified Operation.
     *
     * @param operation The operation to be executed.
     */
    public StatisticForTenPercentCommand(Operation operation) {
        this.operation = operation;
    }

    /**
     * Executes the 10% statistics operation using the provided EventBooker, Halls, and data.
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