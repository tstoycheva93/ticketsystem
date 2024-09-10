package operations.mainOperations;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The `BookSeat` class implements the `Operation` interface and handles the process of booking a seat for a specific event.
 * This operation verifies if the seat is available, and if so, marks it as booked and adds an optional note.
 */
public class BookSeat implements Operation {

    /**
     * Processes the "book" operation. It books a seat for a specific event by date, time, and seat number,
     * and optionally adds a note if the seat is available.
     *
     * @param eventBooker The `EventBooker` object that manages the events.
     * @param halls The `Halls` object that provides information about available halls.
     * @param data The user input data, including the event date, time, seat number, and optional note.
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        if (data.length != 6) {
            throw new IllegalArgumentException("Invalid number of arguments. Enter at least 6.");
        }

        String time = data[3] + " " + data[4];
        String eventName = getEventName(data).toString();
        LocalDateTime date = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        StringBuilder note = getNote(data);

        if (!eventBooker
                .getEventByNameAndDate(eventName, date)
                .getHall()
                .getRows()
                .get(Integer.parseInt(data[1]) - 1)
                .getSeatByNumber(data[2])
                .isBooked()) {
            eventBooker
                    .getEventByNameAndDate(eventName, date)
                    .getHall()
                    .getRows()
                    .get(Integer.parseInt(data[1]) - 1)
                    .getSeatByNumber(data[2])
                    .setNote(note.toString())
                    .setBooked(true);
        } else {
            System.out.println("The seat is already booked.");
        }
    }


    /**
     * Extracts the note from the user input data.
     *
     * @param data The user input data array.
     * @return A `StringBuilder` containing the extracted note.
     */
    private StringBuilder getNote(String[] data) {
        int indexStart = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].charAt(0) == '\"') {
                indexStart = i;
                break;
            }
        }
        StringBuilder note = new StringBuilder();
        for (int i = indexStart; i < data.length - 1; i++) {
            note.append(data[i]).append(" ");
        }
        note.append(data[data.length - 1]);
        return note;
    }

    /**
     * Extracts the event name from the user input data.
     *
     * @param data The user input data array.
     * @return A `StringBuilder` containing the extracted event name.
     */
    private StringBuilder getEventName(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 5; i < data.length; i++) {
            if (data[i].charAt(0) == '\"') {
                break;
            }
            stringBuilder.append(data[i]).append(" ");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder;
    }
}

