package utils;

/**
 * A generic class representing a pair of values.
 * <p>
 * This class can be used to store and manipulate a pair of related values of any types.
 * </p>
 *
 * @param <T> the type of the first value
 * @param <K> the type of the second value
 */
public class Pair<T, K> {
    private T numberOfSeats;
    private K ticketsSold;

    /**
     * Constructs a {@code Pair} with the specified values.
     *
     * @param numberOfSeats the value of the first element in the pair
     * @param ticketsSold the value of the second element in the pair
     */
    public Pair(T numberOfSeats, K ticketsSold) {
        this.numberOfSeats = numberOfSeats;
        this.ticketsSold = ticketsSold;
    }

    /**
     * Returns the value of the first element in the pair.
     *
     * @return the value of the first element
     */
    public T getNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Sets the value of the first element in the pair.
     *
     * @param numberOfSeats the new value for the first element
     * @return the current {@code Pair} instance for method chaining
     */
    public Pair<T, K> setNumberOfSeats(T numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    /**
     * Returns the value of the second element in the pair.
     *
     * @return the value of the second element
     */
    public K getTicketsSold() {
        return ticketsSold;
    }

    /**
     * Sets the value of the second element in the pair.
     *
     * @param ticketsSold the new value for the second element
     * @return the current {@code Pair} instance for method chaining
     */
    public Pair<T, K> setTicketsSold(K ticketsSold) {
        this.ticketsSold = ticketsSold;
        return this;
    }
}
