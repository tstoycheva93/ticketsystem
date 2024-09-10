package menu.commands;

import entities.EventBooker;
import entities.Halls;

/**
 * The Commands interface defines the method for executing commands within the system.
 * Implementations of this interface are responsible for processing specific commands.
 */
public interface Commands {

    /**
     * Executes the command using the provided EventBooker, Halls, and data.
     *
     * @param eventBooker The EventBooker instance.
     * @param halls       The Halls instance.
     * @param data        The data required for the command execution.
     */
    void execute(EventBooker eventBooker, Halls halls, String[] data);
}
