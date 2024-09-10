package entities;

/**
 * The Seat class represents a seat in a row within a hall.
 * It stores information about the seat, such as whether it is booked, payed for, and any associated notes.
 */
public class Seat {
    private String number;
    private boolean booked;
    private boolean payed;
    private String note;

    /**
     * Constructs a Seat with the specified details.
     *
     * @param number The seat number.
     * @param booked A flag indicating whether the seat is booked.
     * @param payed A flag indicating whether the seat is payed for.
     * @param note Any additional notes associated with the seat.
     */
    public Seat(String number, boolean booked, boolean payed, String note) {
        this.number = number;
        this.booked = booked;
        this.payed = payed;
        this.note = note;
    }

    /**
     * Returns the seat number.
     *
     * @return The seat number.
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the seat number.
     *
     * @param number The new seat number.
     * @return The current Seat instance for method chaining.
     */
    public Seat setNumber(String number) {
        this.number = number;
        return this;
    }

    /**
     * Checks if the seat is booked.
     *
     * @return True if the seat is booked, false otherwise.
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * Sets the booked status of the seat.
     *
     * @param booked The new booked status.
     * @return The current Seat instance for method chaining.
     */
    public Seat setBooked(boolean booked) {
        this.booked = booked;
        return this;
    }

    /**
     * Checks if the seat is payed for.
     *
     * @return True if the seat is payed for, false otherwise.
     */
    public boolean isPayed() {
        return payed;
    }

    /**
     * Sets the payed status of the seat.
     *
     * @param payed The new payed status.
     * @return The current Seat instance for method chaining.
     */
    public Seat setPayed(boolean payed) {
        this.payed = payed;
        return this;
    }

    /**
     * Returns any additional notes associated with the seat.
     *
     * @return The note associated with the seat.
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets additional notes for the seat.
     *
     * @param note The new note.
     * @return The current Seat instance for method chaining.
     */
    public Seat setNote(String note) {
        this.note = note;
        return this;
    }
}
