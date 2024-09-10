package entities;

import exceptions.ThereIsNoSuchSeat;

import java.util.ArrayList;
import java.util.List;

/**
 * The Hall class represents a venue where events are held.
 * It manages seating arrangements, ticketing, and information about the hall.
 */
public class Hall {
    private final int NUMBER_OF_SEATS;
    private List<Row> rows;
    private List<Ticket> tickets;
    private String number;
    private final int NUMBER_OF_ROWS;
    private boolean isFull;

    /**
     * Constructs a Hall with the specified number, number of rows, and its full status.
     * Initializes the seating arrangement by creating rows and seats.
     *
     * @param number The number identifier of the hall.
     * @param NUMBER_OF_ROWS The total number of rows in the hall.
     * @param isFull A flag indicating whether the hall is full or not.
     */
    public Hall(String number, int NUMBER_OF_ROWS, boolean isFull) {
        this.rows = new ArrayList<>();
        this.number = number;
        this.NUMBER_OF_ROWS = NUMBER_OF_ROWS;
        this.isFull = isFull;
        this.tickets = new ArrayList<>();
        this.NUMBER_OF_SEATS = 5;
        initializeRows();
    }

    /**
     * Returns the maximum number of tickets that can be issued for this hall.
     *
     * @return The maximum number of tickets.
     */
    public int getMaxAmountOfTickets() {
        return NUMBER_OF_SEATS * NUMBER_OF_ROWS;
    }

    /**
     * Adds a ticket to the list of tickets issued for this hall.
     *
     * @param ticket The ticket to be added.
     * @return The added ticket.
     */
    public Ticket addTicket(Ticket ticket) {
        tickets.add(ticket);
        return ticket;
    }

    /**
     * Removes a ticket from the list of tickets issued for this hall.
     *
     * @param ticket The ticket to be removed.
     */
    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
    }

    /**
     * Returns the list of rows in this hall.
     *
     * @return A list of Row objects.
     */
    public List<Row> getRows() {
        return rows;
    }

    /**
     * Initializes the rows and their corresponding seats in the hall.
     */
    private void initializeRows() {
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            rows.add(new Row(NUMBER_OF_SEATS));
            rows.get(i).initializeSeats(i + 1);
        }
    }

    /**
     * Sets the rows of the hall.
     *
     * @param rows The new list of rows.
     * @return The current Hall instance for method chaining.
     */
    public Hall setRows(List<Row> rows) {
        this.rows = rows;
        return this;
    }

    /**
     * Returns the number identifier of the hall.
     *
     * @return The hall number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the number identifier of the hall.
     *
     * @param number The new hall number.
     * @return The current Hall instance for method chaining.
     */
    public Hall setNumber(String number) {
        this.number = number;
        return this;
    }

    /**
     * Returns the total number of rows in the hall.
     *
     * @return The number of rows.
     */
    public int getNUMBER_OF_ROWS() {
        return NUMBER_OF_ROWS;
    }

    /**
     * Returns whether the hall is full or not.
     *
     * @return True if the hall is full, false otherwise.
     */
    public boolean isFull() {
        return isFull;
    }

    /**
     * Sets the full status of the hall.
     *
     * @param full The new full status.
     * @return The current Hall instance for method chaining.
     */
    public Hall setFull(boolean full) {
        isFull = full;
        return this;
    }

    /**
     * Returns the list of tickets issued for this hall.
     *
     * @return A list of Ticket objects.
     */
    public List<Ticket> getTickets() {
        return tickets;
    }

    /**
     * Sets the list of tickets for this hall.
     *
     * @param tickets The new list of tickets.
     * @return The current Hall instance for method chaining.
     */
    public Hall setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    /**
     * Retrieves a ticket based on the seat number.
     *
     * @param number The seat number associated with the ticket.
     * @return The matching Ticket object.
     * @throws RuntimeException If no matching ticket is found.
     */
    public Ticket getTicketBySeatNumber(String number) {
        for (Ticket ticket : tickets) {
            if (ticket.getSeatNumber().equals(number))
                return ticket;
        }
        throw new ThereIsNoSuchSeat("There is no such seat");
    }
}
