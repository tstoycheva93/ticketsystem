package entities;

import exceptions.ThereIsNoSuchEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The EventBooker class is responsible for managing a list of events.
 * It provides methods for adding, removing, and retrieving events based on
 * various criteria such as name and date.
 */
public class EventBooker {
    private List<Event> events;

    /**
     * Constructs an EventBooker instance with an empty list of events.
     */
    public EventBooker() {
        this.events = new ArrayList<>();
    }

    /**
     * Returns the list of events managed by this EventBooker.
     *
     * @return A list of Event objects.
     */
    public List<Event> getEvents() {
        return events;
    }

    /**
     * Sets the list of events managed by this EventBooker.
     *
     * @param events A list of Event objects to set.
     * @return The current EventBooker instance for method chaining.
     */
    public EventBooker setEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    /**
     * Adds an event to the list of events.
     *
     * @param event The Event to be added.
     */
    public void addEvent(Event event) {
        events.add(event);
    }

    /**
     * Removes a specific event from the list of events.
     *
     * @param event The Event to be removed.
     */
    public void removeEvent(Event event) {
        events.remove(event);
    }

    /**
     * Removes multiple events from the list of events.
     *
     * @param events A list of Event objects to be removed.
     */
    public void removeEvents(List<Event> events) {
        this.events.removeAll(events);
    }

    /**
     * Retrieves an event based on its name and date.
     *
     * @param name The name of the event.
     * @param time The date and time of the event.
     * @return The matching Event object.
     * @throws RuntimeException If no matching event is found.
     */
    public Event getEventByNameAndDate(String name, LocalDateTime time) {
        for (Event event : events) {
            if (event.getName().equals(name) && event.getDate().isEqual(time)) {
                return event;
            }
        }
        throw new ThereIsNoSuchEvent("There is no such event");
    }

    /**
     * Retrieves a list of events that match a specific name.
     *
     * @param name The name of the event(s) to retrieve.
     * @return A list of matching Event objects.
     */
    public List<Event> getEventByName(String name) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getName().equals(name)) {
                result.add(event);
            }
        }
        return result;
    }

    /**
     * Retrieves a list of events that occur on a specific date.
     *
     * @param time The date and time of the event(s) to retrieve.
     * @return A list of matching Event objects.
     */
    public List<Event> getEventByDate(LocalDateTime time) {
        List<Event> result = new ArrayList<>();
        for (Event event : events) {
            if (event.getDate().isEqual(time)) {
                result.add(event);
            }
        }
        return result;
    }

    /**
     * Prints details of booked seats for a list of events.
     *
     * @param eventByName A list of events for which booked seat details should be printed.
     */
    public void printDetailsForBookedSeats(List<Event> eventByName) {
        for (Event event : eventByName) {
            int counter = 0;
            for (Row row : event.getHall().getRows()) {
                counter++;
                for (Seat seat : row.getSeats()) {
                    if (seat.isBooked()) {
                        System.out.println("On row number: " + counter
                                + " seat with number " + seat.getNumber()
                                + " is booked with note: " + seat.getNote());
                    }
                }
            }
        }
    }
}
