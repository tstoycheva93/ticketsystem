package operations.mainOperations;

import entities.*;
import operations.interfaces.Operation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code Report} class implements the {@code Operation} interface and generates a report of tickets sold
 * for events within a specified date range. It can also filter the report by a specific hall if provided.
 * <p>
 * The class processes the input data to filter events based on their dates and optionally by hall number,
 * and prints ticket details for the filtered events.
 * </p>
 */
public class Report implements Operation {

    /**
     * Processes the report generation by filtering events within a specified date range and optionally by hall number.
     * <p>
     * If a hall number is provided in the input data, the report is filtered to include only events in that hall.
     * Otherwise, it includes events from all halls. The method prints ticket details for the filtered events.
     * </p>
     *
     * @param eventBooker the {@code EventBooker} instance used to retrieve event and ticket information
     * @param halls the {@code Halls} instance used to retrieve hall information (not used in this method)
     * @param data an array of {@code String} containing command data. The array should include:
     *             <ul>
     *             <li>From date and time (index 1 and 2)</li>
     *             <li>To date and time (index 3 and 4)</li>
     *             <li>Optional hall number (index 5)</li>
     *             </ul>
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        String from = data[1] + " " + data[2];
        String to = data[3] + " " + data[4];
        LocalDateTime fromTime = convertTime(from);
        LocalDateTime toTime = convertTime(to);
        if(fromTime.isAfter(toTime)) { //SWAP
            //fromTime 2024
            //to time 2022
            LocalDateTime temp = fromTime;
            fromTime = toTime;
            toTime = temp;
        }
        if (data.length == 5) {
            //no hall number, group events by hall and print ticket details
            Map<String, List<Event>> hallsToEvents = new HashMap<>();
            for (Event event : eventBooker.getEvents()) {
                if (checkDates(fromTime, toTime, event)) {
                    if (hallsToEvents.containsKey(event.getHall().getNumber())) {
                        hallsToEvents.get(event.getHall().getNumber()).add(event);
                    } else {
                        hallsToEvents.put(event.getHall().getNumber(), new ArrayList<>());
                        hallsToEvents.get(event.getHall().getNumber()).add(event);
                    }
                }
            }
            for (Map.Entry<String, List<Event>> stringListEntry : hallsToEvents.entrySet()) {
                System.out.println("Hall " + stringListEntry.getKey());
                List<Event> value = stringListEntry.getValue();
                printTicketDetails(value);
            }
        } else if (data.length == 6) {
            // hall number, filter events by hall number and print ticket details
            List<Event> result = new ArrayList<>();
            for (Event event : eventBooker.getEvents()) {
                if (checkDates(fromTime, toTime, event)) {
                    if (event.getHall().getNumber().equals(data[5])) {
                        result.add(event);
                    }
                }
            }
            printTicketDetails(result);
        }
    }

    /**
     * Converts a date and time string into a {@code LocalDateTime} object using the pattern "yyyy-MM-dd HH:mm:ss".
     *
     * @param time the date and time string to be converted
     * @return the corresponding {@code LocalDateTime} object
     */
    private LocalDateTime convertTime(String time) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Checks if an event's date is within the specified date range.
     *
     * @param fromTime the start of the date range
     * @param toTime the end of the date range
     * @param event the {@code Event} to be checked
     * @return {@code true} if the event's date is within the range or equal to the range boundaries, {@code false} otherwise
     */
    private boolean checkDates(LocalDateTime fromTime, LocalDateTime toTime, Event event) {
        boolean range = event.getDate().isBefore(toTime) && event.getDate().isAfter(fromTime);
        return range || event.getDate().isEqual(toTime) || event.getDate().isEqual(fromTime);
    }

    /**
     * Prints ticket details for a list of events.
     *
     * @param value a {@code List} of {@code Event} objects whose tickets' details are to be printed
     */
    private void printTicketDetails(List<Event> value) {
        for (Event event : value) {
            for (Ticket ticket : event.getHall().getTickets()) {
                System.out.println("Ticket info: " + ticket.getCode() + "\n" + ticket.getSeatNumber());
            }
        }
    }
}
