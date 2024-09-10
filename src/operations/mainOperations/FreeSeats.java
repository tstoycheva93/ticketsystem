package operations.mainOperations;

import entities.*;
import operations.interfaces.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code FreeSeats} class implements the {@code Operation} interface and
 * handles the retrieval of free seats information for a given event.
 * <p>
 * This operation checks the availability of seats for a specific event at
 * a given date and time. It prints the number of unbooked and unpurchased
 * tickets for each row of the hall associated with the event.
 * </p>
 */
public class FreeSeats implements Operation {

    /**
     * Processes the retrieval of free seats information for an event.
     * <p>
     * The data array is expected to contain the following elements:
     * <ul>
     *     <li>[0] - Command (freeseats)</li>
     *     <li>[1] - Date (yyyy-MM-dd)</li>
     *     <li>[2] - Time (HH:mm:ss)</li>
     *     <li>[3] - Event name</li>
     * </ul>
     * </p>
     *
     * @param eventBooker the {@code EventBooker} instance used to retrieve event and seat information
     * @param halls the {@code Halls} instance used to retrieve hall information (not used in this method)
     * @param data an array of {@code String} containing the freeseats command, date, time, and event name:
     *             [0] - command (freeseats),
     *             [1] - date (yyyy-MM-dd),
     *             [2] - time (HH:mm:ss),
     *             [3] - event name
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        String time = data[1] + " " + data[2];
        String eventName = getEventName(data).toString();
        LocalDateTime date = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Event event = eventBooker.getEventByNameAndDate(eventName, date);
        for (int i = 0; i < event.getHall().getRows().size(); i++) {
            System.out.printf("Row %d has ", i + 1);
            int unbooked = 0, unpurchased = 0;
            for (Seat seat : event.getHall().getRows().get(i).getSeats()) {
                if (!seat.isBooked()) unbooked++;
                if (!seat.isPayed()) unpurchased++;
            }
            System.out.printf("%d unbooked and %d unpurchased tickets%n", unbooked, unpurchased);
        }
    }

    /**
     * Extracts the event name from the provided data array.
     * <p>
     * This method concatenates all elements from index 3 to the end of the data array
     * to form the event name, assuming the event name may contain spaces.
     * </p>
     *
     * @param data an array of {@code String} containing the command data
     * @return a {@code StringBuilder} containing the event name
     */
    private StringBuilder getEventName(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 3; i < data.length - 1; i++) {
            stringBuilder.append(data[i]).append(' ');
        }
        stringBuilder.append(data[data.length - 1]);
        return stringBuilder;
    }
}
