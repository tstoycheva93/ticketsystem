package entities;

import java.util.Random;

/**
 * The Ticket class represents a ticket issued for a seat in a hall.
 * It stores a unique code and the associated seat number.
 */
public class Ticket {
    private String code;
    private String seatNumber;

    /**
     * Constructs a Ticket for the specified seat number.
     * Generates a unique code for the ticket.
     *
     * @param seatNumber The seat number associated with this ticket.
     */
    public Ticket(String seatNumber) {
        this.code = generateCode();
        this.seatNumber = seatNumber;
    }

    /**
     * Returns the unique code of the ticket.
     *
     * @return The ticket code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the unique code of the ticket.
     *
     * @param code The new ticket code.
     * @return The current Ticket instance for method chaining.
     */
    public Ticket setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Returns the seat number associated with the ticket.
     *
     * @return The seat number.
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * Sets the seat number associated with the ticket.
     *
     * @param seatNumber The new seat number.
     * @return The current Ticket instance for method chaining.
     */
    public Ticket setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    /**
     * Generates a unique alphanumeric code for the ticket.
     *
     * @return A randomly generated code.
     */
    private String generateCode() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        int size = 5;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int index = random.nextInt(alphabet.length());
            code.append(alphabet.charAt(index));
        }
        return code.toString();
    }
}
