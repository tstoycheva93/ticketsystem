package operations.mainOperations;

import entities.Event;
import entities.EventBooker;
import entities.Halls;
import entities.Row;
import operations.interfaces.Operation;
import utils.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The {@code StatisticForTenPercent} class is responsible for processing events and performing statistical
 * analysis based on ticket sales. Specifically, it calculates the percentage of tickets sold for each event and
 * prompts the user to remove events where the percentage of tickets sold is less than 10%.
 * <p>
 * This class implements the {@code Operation} interface and overrides the {@code process} method to handle the
 * logic for analyzing events. The class calculates the percentage of sold tickets by comparing the total number
 * of seats available in the event's hall with the number of tickets sold. If an event does not meet the minimum
 * threshold of 10% of tickets sold, the user is prompted with the option to remove that event.
 * <p>
 * This class interacts with the {@code EventBooker} instance to retrieve and remove events, and with the {@code Halls}
 * instance to retrieve hall information, although the latter is not directly used in the processing method.
 */
public class StatisticForTenPercent implements Operation {

    /**
     * Processes the events to analyze ticket sales and prompts the user to remove events with less than 10% ticket sales.
     * <p>
     * The method iterates through all the events and calculates the total number of seats available and the number of tickets sold
     * for each event. It then calculates the percentage of tickets sold for each event. If the percentage is less than 10%,
     * the user is prompted to decide whether to remove the event.
     * <p>
     * The events and their ticket sales data are stored in a map where the key is the event name, and the value is a {@code Pair}
     * representing the total number of seats and the number of tickets sold. If an event appears multiple times, its ticket
     * sales data is aggregated.
     *
     * @param eventBooker the {@code EventBooker} instance used to retrieve and modify event information
     * @param halls       the {@code Halls} instance used to retrieve hall information (not used in this method)
     * @param data        an array of {@code String} containing command data (not used in this method)
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        Scanner scanner = new Scanner(System.in);

        // initialize map to store event data: event name -> (number of seats, tickets sold)
        Map<String, Pair<Integer, Integer>> eventTickets = new HashMap<>();

        // Populate the map with ticket sales data for each event
        for (Event event : eventBooker.getEvents()) {
            if (!eventTickets.containsKey(event.getName())) {
                int ticketsSold = event.getHall().getTickets().size();
                int numberOfSeats = calculateNumberOfSeats(event);
                Pair<Integer, Integer> pair = new Pair<>(numberOfSeats, ticketsSold);
                eventTickets.put(event.getName(), pair);
            } else {
                Pair<Integer, Integer> pair = eventTickets.get(event.getName());
                int numberOfSeats = pair.getNumberOfSeats() + calculateNumberOfSeats(event);
                pair.setNumberOfSeats(numberOfSeats);
                pair.setTicketsSold(pair.getTicketsSold() + event.getHall().getTickets().size());
                eventTickets.put(event.getName(), pair);
            }
        }

        // Analyze events and prompt user to remove events with less than 10% ticket sales
        for (Map.Entry<String, Pair<Integer, Integer>> entry : eventTickets.entrySet()) {
            double percentage = calculatePercentage(entry.getValue());
            if (percentage < 10.00) {
                String eventName = entry.getKey();
                System.out.println("Do you wish to remove this event (yes/no): " + eventName);
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    eventBooker.removeEvents(eventBooker.getEventByName(entry.getKey()));
                }
            }
        }
    }

    /**
     * Calculates the percentage of tickets sold for an event.
     * <p>
     * This method takes a {@code Pair} object containing the number of seats and the number of tickets sold for an event
     * and calculates the percentage of tickets sold by dividing the tickets sold by the total number of seats and multiplying by 100.
     *
     * @param pair a {@code Pair} object containing the number of seats and tickets sold for an event
     * @return the percentage of tickets sold for the event
     */
    private double calculatePercentage(Pair<Integer, Integer> pair) {
        return (pair.getTicketsSold() / (double) pair.getNumberOfSeats()) * 100.0;
    }

    /**
     * Calculates the total number of seats available for an event by summing the seats in each row of the event's hall.
     * <p>
     * This method iterates through the rows of the event's hall and sums up the number of seats in each row to get the total number of seats.
     *
     * @param event the {@code Event} for which the total number of seats is to be calculated
     * @return the total number of seats available in the event's hall
     */
    private int calculateNumberOfSeats(Event event) {
        int numberOfSeats = 0;
        for (Row row : event.getHall().getRows()) {
            numberOfSeats += row.getSeats().size();
        }
        return numberOfSeats;
    }
}
