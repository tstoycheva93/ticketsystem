package operations.mainOperations;

import entities.Event;
import entities.EventBooker;
import entities.Halls;
import entities.Ticket;
import exceptions.TicketException;
import operations.interfaces.Operation;

/**
 * The {@code Check} class implements the {@code Operation} interface and
 * handles the validation of ticket codes.
 * <p>
 * This operation verifies if a ticket code is valid by checking if it exists
 * in the list of tickets for any event. If valid, it prints the event details;
 * otherwise, it throws a {@code TicketException}.
 * </p>
 */
public class Check implements Operation {

    /**
     * Processes the validation of a ticket code based on the provided data.
     * <p>
     * The data array is expected to contain the following elements:
     * <ul>
     *     <li>[0] - Command (check)</li>
     *     <li>[1] - Ticket code</li>
     * </ul>
     * </p>
     *
     * @param eventBooker the {@code EventBooker} instance used to retrieve event and ticket information
     * @param halls the {@code Halls} instance used to retrieve hall information (not used in this method)
     * @param data an array of {@code String} containing the check command and the ticket code:
     *             [0] - command (check),
     *             [1] - ticket code
     * @throws TicketException if the ticket code is invalid
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        for (Event event : eventBooker.getEvents()) {
            for (Ticket ticket : event.getHall().getTickets()) {
                if (ticket.getCode().equals(data[1])) {
                    System.out.println("Valid code");
                    System.out.println(event);
                    return;
                }
            }
        }
        throw new TicketException("Invalid code");
    }
}
