package operations.mainOperations;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The {@code UnbookSeat} class implements the {@code Operation} interface and provides functionality
 * to unbook a seat for a specific event. It processes the input data to identify the event and seat,
 * and then unbooks the seat if it is currently booked.
 * <p>
 * The class performs the following operations:
 * <ul>
 *     <li>Parses the event date and time.</li>
 *     <li>Retrieves the event and seat information.</li>
 *     <li>Unbooks the seat if it is currently booked; otherwise, prints a message indicating the seat is not booked.</li>
 * </ul>
 * </p>
 */
public class UnbookSeat implements Operation {
    /**
     * Processes the input data to unbook a seat for a specific event.
     * <p>
     * The method parses the event date and time, retrieves the event and seat information, and
     * unbooks the seat if it is currently booked. If the seat is not booked, a message is printed
     * indicating that the seat is not booked.
     * </p>
     *
     * @param eventBooker the {@code EventBooker} instance used to retrieve and modify event information
     * @param halls the {@code Halls} instance used to retrieve hall information (not used in this method)
     * @param data an array of {@code String} containing command data. The expected format is:
     *             {@code ["unbook", row, seatNumber, date, time, eventName]}
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        // unbook 3 2 2024-12-30 12:45:12 tedi
        String time = data[3] + " " + data[4];
        String eventName = getEventName(data).toString();
        LocalDateTime date = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        if (eventBooker.getEventByNameAndDate(eventName, date)
                .getHall()
                .getRows()
                .get(Integer.parseInt(data[1]) - 1)
                .getSeatByNumber(data[2])
                .isBooked()) {

            eventBooker.getEventByNameAndDate(eventName, date)
                    .getHall()
                    .getRows()
                    .get(Integer.parseInt(data[1]) - 1)
                    .getSeatByNumber(data[2])
                    .setBooked(false);
        } else {
            System.out.println("The seat is not booked");
        }
    }

    /**
     * Constructs the event name from the provided data array.
     * <p>
     * The method concatenates the elements of the data array to form the full event name.
     * </p>
     *
     * @param data an array of {@code String} containing command data
     * @return a {@code StringBuilder} containing the event name
     */
    private StringBuilder getEventName(String[] data) {
        StringBuilder eventName = new StringBuilder();
        for (int i = 5; i < data.length - 1; i++) {
            eventName.append(data[i]).append(' ');
        }
        eventName.append(data[data.length - 1]);
        return eventName;
    }
}
