package operations.mainOperations;

import entities.Event;
import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

import java.util.HashMap;
import java.util.Map;

/**
 * The `MostFamous` class implements the `Operation` interface and is responsible for identifying the
 * most popular event based on the total number of tickets sold.
 * <p>
 * This operation iterates over all events, accumulates the number of tickets sold for each event,
 * and determines which event has the highest number of sold tickets. The name of the most popular
 * event and the number of sold tickets are then printed to the console.
 * </p>
 */
public class MostFamous implements Operation {

    /**
     * Processes the operation to identify the most popular event by calculating the total number of
     * tickets sold for each event and determining the event with the highest number of sold tickets.
     * <p>
     * The method iterates through all events stored in the `EventBooker` instance, calculates the total
     * number of tickets sold for each event, and identifies the event with the highest sales.
     * Finally, it prints the name of the most watched event and the number of sold tickets.
     * </p>
     *
     * @param eventBooker The `EventBooker` instance that provides access to all events and their ticket data.
     * @param halls The `Halls` instance that contains hall information (not used in this operation).
     * @param data A string array containing command data (not used in this operation).
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        Map<String, Integer> soldTicketForShow = new HashMap<>();

        // Calculate the total number of tickets sold for each event
        for (Event event : eventBooker.getEvents()) {
            int ticketsSold = event.getHall().getTickets().size();
            soldTicketForShow.merge(event.getName(), ticketsSold, Integer::sum);
        }

        // Identify the event with the most tickets sold
        String mostWatched = "";
        int mostBought = 0;
        for (Map.Entry<String, Integer> entry : soldTicketForShow.entrySet()) {
            if (entry.getValue() > mostBought) {
                mostBought = entry.getValue();
                mostWatched = entry.getKey();
            }
        }

        // Print the result
        System.out.println("The most watched event is: " + mostWatched + " with " + mostBought + " tickets sold.");
    }
}
