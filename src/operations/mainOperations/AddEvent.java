package operations.mainOperations;

import entities.*;
import exceptions.EventException;
import operations.interfaces.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The {@code AddEvent} class implements the {@code Operation} interface and
 * handles the addition of a new event to the {@code EventBooker}.
 * <p>
 * This operation parses the provided data to create and add a new {@code Event}
 * to the {@code EventBooker}. It checks for duplicate events and clones the hall
 * information to ensure isolation.
 * </p>
 */
public class AddEvent implements Operation {

    /**
     * Processes the addition of a new event to the {@code EventBooker}.
     * <p>
     * Parses the date and time, constructs the event name, and creates a new {@code Event}.
     * If an event with the same name already exists, an {@code EventException} is thrown.
     * </p>
     *
     * @param eventBooker the {@code EventBooker} instance to which the event will be added
     * @param halls the {@code Halls} instance used to retrieve hall information
     * @param data an array of {@code String} containing the event details:
     *             [0] - command (addevent),
     *             [1] - event date (yyyy-MM-dd),
     *             [2] - event time (HH:mm:ss),
     *             [3] - hall name,
     *             [4] - event name (can be multiple words)
     * @throws EventException if an event with the same name already exists
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        String time = data[1] + " " + data[2];
        LocalDateTime date = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String eventName = getEventName(data).toString();

        Hall hallByName = halls.getHallByName(data[3]);
        Hall newHall = manualClone(hallByName);
        Event event = new Event(date, eventName, newHall);

        for (Event eventBookerEvent : eventBooker.getEvents()) {
            if (eventBookerEvent.getName().equals(eventName)) {
                throw new EventException("Event already exists");
            }
        }
        eventBooker.addEvent(event);
    }

    /**
     * Constructs the event name from the provided data.
     * <p>
     * Joins all elements from the 4th element to the end of the array to form the event name.
     * </p>
     *
     * @param data an array of {@code String} containing the command data
     * @return a {@code StringBuilder} containing the event name
     */
    private StringBuilder getEventName(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 4; i < data.length - 1; i++) {
            stringBuilder.append(data[i]).append(' ');
        }
        stringBuilder.append(data[data.length - 1]);
        return stringBuilder;
    }

    /**
     * Creates a manual clone of the specified {@code Hall}.
     * <p>
     * Initializes a new {@code Hall} object with the same properties as the provided hall
     * and sets an empty list for tickets.
     * </p>
     *
     * @param hall the {@code Hall} to be cloned
     * @return a new {@code Hall} instance that is a clone of the provided hall
     */
    private Hall manualClone(Hall hall) {
        Hall hallCloned = new Hall(hall.getNumber(), hall.getNUMBER_OF_ROWS(), hall.isFull());
        hallCloned.setTickets(new ArrayList<>());
        return hallCloned;
    }
}

