package entities;

import exceptions.ThereIsNoSuchSeat;

import java.util.ArrayList;
import java.util.List;

/**
 * The Row class represents a row of seats in a hall.
 * It manages the seats within the row and provides methods to add, remove, and initialize seats.
 */
public class Row {
    private final int NUMBER_OF_SEATS;
    private List<Seat> seats;

    /**
     * Constructs a Row with a specified number of seats.
     *
     * @param NUMBER_OF_SEATS The total number of seats in this row.
     */
    public Row(int NUMBER_OF_SEATS) {
        this.NUMBER_OF_SEATS = NUMBER_OF_SEATS;
        this.seats = new ArrayList<>();
    }

    /**
     * Adds a seat to the row.
     *
     * @param seat The Seat object to be added.
     */
    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    /**
     * Removes a seat from the row.
     *
     * @param seat The Seat object to be removed.
     */
    public void removeSeat(Seat seat) {
        this.seats.remove(seat);
    }

    /**
     * Initializes the seats in the row by assigning seat numbers based on the row number.
     *
     * @param number The row number used to generate seat numbers.
     */
    public void initializeSeats(int number) {
        char start = 'A';
        for (int i = 0; i < NUMBER_OF_SEATS; i++) {
            seats.add(new Seat(String.valueOf(number + String.valueOf(start++)), false, false, ""));
        }
    }

    /**
     * Returns the total number of seats in the row.
     *
     * @return The number of seats.
     */
    public int getNUMBER_OF_SEATS() {
        return NUMBER_OF_SEATS;
    }

    /**
     * Retrieves a seat by its seat number.
     *
     * @param number The seat number to search for.
     * @return The matching Seat object.
     * @throws RuntimeException If no seat with the given number is found.
     */
    public Seat getSeatByNumber(String number) {
        for (Seat seat : seats) {
            if (seat.getNumber().equals(number)) {
                return seat;
            }
        }
        throw new ThereIsNoSuchSeat("There is no such seat");
    }

    /**
     * Returns the list of seats in the row.
     *
     * @return A list of Seat objects.
     */
    public List<Seat> getSeats() {
        return seats;
    }

    /**
     * Sets the list of seats in the row.
     *
     * @param seats The new list of seats.
     * @return The current Row instance for method chaining.
     */
    public Row setSeats(List<Seat> seats) {
        this.seats = seats;
        return this;
    }
}
