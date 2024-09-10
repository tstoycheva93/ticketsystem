package operations.mainOperations;

import entities.Event;
import entities.EventBooker;
import entities.Halls;
import entities.Ticket;
import operations.interfaces.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code BuyTicket} class implements the {@code Operation} interface and
 * handles the purchasing of tickets for a specified event.
 * <p>
 * This operation processes the purchase of a ticket by marking a seat as paid and
 * generating a ticket code. The details are specified in the {@code data} array.
 * </p>
 */
public class BuyTicket implements Operation {

    /**
     * Processes the purchase of a ticket for a specific event based on the provided data.
     * <p>
     * The data array is expected to contain the following elements:
     * <ul>
     *     <li>[0] - Command (buy)</li>
     *     <li>[1] - Row number (1-based index)</li>
     *     <li>[2] - Seat number</li>
     *     <li>[3] - Event date (yyyy-MM-dd)</li>
     *     <li>[4] - Event time (HH:mm:ss)</li>
     *     <li>[5] - Event name</li>
     *     <li>[6+] - Optional note (not used in this method)</li>
     * </ul>
     * </p>
     *
     * @param eventBooker the {@code EventBooker} instance used to retrieve event and booking information
     * @param halls the {@code Halls} instance used to retrieve hall information (not used in this method)
     * @param data an array of {@code String} containing the ticket purchase details:
     *             [0] - command (buy),
     *             [1] - row number (1-based index),
     *             [2] - seat number,
     *             [3] - event date (yyyy-MM-dd),
     *             [4] - event time (HH:mm:ss),
     *             [5] - event name,
     *             [6+] - note (if any)
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        //buy 2 3A 2024-02-01 14:10:20 az
        String time = data[3] + " " + data[4];
        StringBuilder stringBuilder = getEventName(data);
        String name = stringBuilder.toString();
        LocalDateTime date = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (!eventBooker.getEventByNameAndDate(name, date).getHall().isFull()) {
            // not full, add the ticket to the list of sold tickets
            eventBooker
                    .getEventByNameAndDate(name, date)
                    .getHall()
                    .getRows()
                    .get(Integer.parseInt(data[1]) - 1)
                    .getSeatByNumber(data[2])
                    .setPayed(true);

            Ticket ticket = eventBooker.getEventByNameAndDate(name, date)
                    .getHall()
                    .addTicket(new Ticket(data[2]));

            System.out.println("This is your code: " + ticket.getCode());
            Event event = eventBooker.getEventByNameAndDate(name, date);
            int size = event.getHall().getTickets().size();
            if (size == event.getHall().getMaxAmountOfTickets()) {
                event.getHall().setFull(true);
            }
        } else {
            System.out.println("The current event is full");
        }
    }

    /**
     * Constructs the event name from the provided data array.
     * <p>
     * The event name starts at index 5 and continues until the end of the data array.
     * </p>
     *
     * @param data an array of {@code String} containing the command and its parameters
     * @return a {@code StringBuilder} containing the concatenated event name
     */
    private StringBuilder getEventName(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 5; i < data.length - 1; i++) {
            stringBuilder.append(data[i]).append(' ');
        }
        stringBuilder.append(data[data.length - 1]);
        return stringBuilder;
    }
}
