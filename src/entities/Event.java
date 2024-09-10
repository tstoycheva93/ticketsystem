package entities;

import java.time.LocalDateTime;

/**
 * Represents an event with a specific date, name, and location (hall).
 */
public class Event {
    private LocalDateTime date;
    private String name;
    private Hall hall;

    /**
     * Constructs an Event with the specified date, name, and hall.
     *
     * @param date The date and time of the event.
     * @param name The name of the event.
     * @param hall The hall where the event will take place.
     */
    public Event(LocalDateTime date, String name, Hall hall) {
        this.date = date;
        this.name = name;
        this.hall = hall;
    }

    /**
     * Returns the date and time of the event.
     *
     * @return The date and time of the event.
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date and time of the event.
     *
     * @param date The new date and time of the event.
     * @return The current Event instance for method chaining.
     */
    public Event setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    /**
     * Returns the name of the event.
     *
     * @return The name of the event.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the event.
     *
     * @param name The new name of the event.
     * @return The current Event instance for method chaining.
     */
    public Event setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the hall where the event will take place.
     *
     * @return The hall of the event.
     */
    public Hall getHall() {
        return hall;
    }

    /**
     * Sets the hall where the event will take place.
     *
     * @param hall The new hall for the event.
     * @return The current Event instance for method chaining.
     */
    public Event setHall(Hall hall) {
        this.hall = hall;
        return this;
    }

    /**
     * Returns a string representation of the event.
     * The string includes the event name, date, and hall number.
     *
     * @return A string representation of the event.
     */
    @Override
    public String toString() {
        return "Event: " + name + "\n"
                + "on: " + date + "\n"
                + "in hall: " + "Hall-"+hall.getNumber();
    }
}
