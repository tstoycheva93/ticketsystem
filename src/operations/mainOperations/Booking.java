package operations.mainOperations;

import entities.*;
import operations.interfaces.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The `Booking` class implements the `Operation` interface and handles the "bookings" operation,
 * which involves listing all booked seats for events. Depending on the provided input, it can:
 * - List all bookings for all events.
 * - List bookings for a specific event identified by its name.
 * - List bookings for a specific event at a specified date and time.
 * <p>
 * This class interacts with the `EventBooker` object to retrieve and display booking information.
 * The class processes different command structures based on the number and format of the input arguments.
 */
public class Booking implements Operation {

    /**
     * Processes the "bookings" operation to display booked seats. Depending on the input arguments, the method:
     * - Lists all bookings if no additional arguments are provided.
     * - Lists bookings for a specific event if the event name is provided.
     * - Lists bookings for a specific event at a specified date and time if both the event name and date are provided.
     *
     * @param eventBooker The `EventBooker` object that stores and manages events.
     * @param halls       The `Halls` object that provides information about available halls.
     * @param data        The user input data, which can include the event name, date, or both.
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {

        if (data.length == 1) {
            // Only "bookings" - list all bookings for all events
            for (Event event : eventBooker.getEvents()) {
                for (Row row : event.getHall().getRows()) {
                    for (Seat seat : row.getSeats()) {
                        if (seat.isBooked()) {
                            System.out.println(event);
                            System.out.println("With note: " + seat.getNote());
                        }
                    }
                }
            }
        } else if (data.length == 2) {
            // Has event name - list bookings for the specified event
            printOnlyByName(eventBooker.getEventByName(data[1]), eventBooker);
        } else if (data.length == 3) {
            // Has event name and possibly a date - check and list bookings accordingly
            if (checkIfOnlyData(eventBooker, data)) return;
            printOnlyByName(eventBooker.getEventByName(data[1] + " " + data[2]), eventBooker);
        } else {
            // Handle cases with more input arguments
            if (checkForDate(data)) {
                String time = data[1] + " " + data[2];
                LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                StringBuilder stringBuilder = getNameIfExists(data);
                if (!stringBuilder.isEmpty()) {
                    printByNameAndDate(eventBooker, stringBuilder, dateTime);
                    return;
                }

                printOnlyByName(eventBooker.getEventByDate(dateTime), eventBooker);
            } else {
                StringBuilder stringBuilder = getName(data);
                printOnlyByName(eventBooker.getEventByName(stringBuilder.toString()), eventBooker);
            }
        }
    }

    /**
     * Constructs an event name from the provided input data.
     * <p>
     * This method concatenates the input data (except the last element) to build the event name.
     *
     * @param data The input data array.
     * @return A `StringBuilder` containing the constructed event name.
     */
    private StringBuilder getName(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < data.length - 1; i++) {
            stringBuilder.append(data[i]).append(" ");
        }
        stringBuilder.append(data[data.length - 1]);
        return stringBuilder;
    }

    /**
     * Prints the booking details for an event identified by its name and date.
     * <p>
     * This method retrieves the event using both its name and date, then prints the details
     * of all booked seats for that event.
     *
     * @param eventBooker    The `EventBooker` object that stores and manages events.
     * @param stringBuilder  A `StringBuilder` containing the event name.
     * @param dateTime       The date and time of the event.
     */
    private void printByNameAndDate(EventBooker eventBooker, StringBuilder stringBuilder, LocalDateTime dateTime) {
        Event event = eventBooker.getEventByNameAndDate(stringBuilder.toString(), dateTime);
        eventBooker.printDetailsForBookedSeats(List.of(event));
    }

    /**
     * Constructs an event name from the provided input data, if the data includes a name.
     * <p>
     * This method constructs the event name starting from the 4th element in the input array.
     *
     * @param data The input data array.
     * @return A `StringBuilder` containing the constructed event name, if available.
     */
    private StringBuilder getNameIfExists(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 3; i < data.length - 1; i++) {
            stringBuilder.append(data[i]).append(" ");
        }
        if (!data[2].equals(data[data.length - 1])) {
            stringBuilder.append(data[data.length - 1]);
        }
        return stringBuilder;
    }

    /**
     * Prints the booking details for a list of events.
     * <p>
     * This method retrieves and prints the booking details for all booked seats in the provided list of events.
     *
     * @param eventBooker The list of events to print booking details for.
     * @param eventBooker1 The `EventBooker` object that stores and manages events.
     */
    private void printOnlyByName(List<Event> eventBooker, EventBooker eventBooker1) {
        eventBooker1.printDetailsForBookedSeats(eventBooker);
    }

    /**
     * Checks if the provided input data contains only a date.
     * <p>
     * If the input data contains only a date, this method retrieves the event corresponding to that date and prints its booking details.
     *
     * @param eventBooker The `EventBooker` object that stores and manages events.
     * @param data        The input data array.
     * @return True if the data contains only a date, false otherwise.
     */
    private boolean checkIfOnlyData(EventBooker eventBooker, String[] data) {
        if (checkForDate(data)) {
            String time = data[1] + " " + data[2];
            LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            printOnlyByName(eventBooker.getEventByDate(dateTime), eventBooker);
            return true;
        }
        return false;
    }

    /**
     * Checks if the provided input data contains a valid date.
     * <p>
     * This method attempts to parse the input data as a date using the "yyyy-MM-dd HH:mm:ss" format.
     *
     * @param data The input data array.
     * @return True if the data contains a valid date, false otherwise.
     */
    private boolean checkForDate(String[] data) {
        try {
            String time = data[1] + " " + data[2];
            LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
