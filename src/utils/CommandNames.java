package utils;

/**
 * Enumeration representing various command names used in the system.
 * <p>
 * This enum contains specific commands related to event booking and management,
 * as well as base commands for file operations and help functionality.
 * </p>
 */
public enum CommandNames {
    // Specific commands
    /** Command to add a new event. */
    ADD_EVENT_COMMAND("addevent"),

    /** Command to list all bookings. */
    BOOKING_COMMAND("bookings"),

    /** Command to book a specific seat for an event. */
    BOOK_SEAT_COMMAND("book"),

    /** Command to buy a ticket for an event. */
    BUY_TICKET_COMMAND("buy"),

    /** Command to check the validity of a ticket code. */
    CHECK_COMMAND("check"),

    /** Command to display free seats for a specific event. */
    FREE_SEATS_COMMAND("freeseats"),

    /** Command to find the most famous event based on ticket sales. */
    MOST_FAMOUS_COMMAND("mostfamous"),

    /** Command to generate a report for events within a date range. */
    REPORT_COMMAND("report"),

    /** Command to display statistics for events with less than 10% ticket sales. */
    STATISTIC_FOR_TEN_PERCENT_COMMAND("statistic"),

    /** Command to unbook a previously booked seat. */
    UNBOOK_SEAT_COMMAND("unbook"),

    // Base commands
    /** Command to open a file. */
    OPEN("open"),

    /** Command to close the current file. */
    CLOSE("close"),

    /** Command to save the current file. */
    SAVE("save"),

    /** Command to save the current file with a new name. */
    SAVEAS("saveas"),

    /** Command to display help information. */
    HELP("help"),

    /** Command to exit the application. */
    EXIT("exit");

    private final String code;

    /**
     * Constructs a {@code CommandNames} enum with the specified code.
     *
     * @param code the code associated with the command
     */
    CommandNames(String code) {
        this.code = code;
    }

    /**
     * Gets the code associated with this command.
     *
     * @return the code of the command
     */
    public String getCode() {
        return code;
    }

    /**
     * Retrieves the {@code CommandNames} enum constant corresponding to the specified code.
     *
     * @param code the code of the command
     * @return the {@code CommandNames} enum constant, or {@code null} if no constant matches the code
     */
    public static CommandNames getByCode(String code) {
        for (CommandNames value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }
}
