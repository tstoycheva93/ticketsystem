package operations.baseOperation;

import entities.Event;
import entities.EventBooker;
import entities.Hall;
import entities.Halls;
import exceptions.FileException;
import operations.interfaces.Operation;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Open` class is responsible for processing the "open" operation.
 * This operation reads event data from a specified file and populates the `EventBooker` with events.
 * The data loaded includes event names, event dates, and the halls where the events are held.
 */
public class Open implements Operation {

    /**
     * Processes the "open" operation. This method reads the file specified by the user,
     * extracts event data including the event name, date, and associated hall,
     * and populates the `EventBooker` with new `Event` objects.
     *
     * @param eventBooker The `EventBooker` object that stores the events.
     * @param halls The `Halls` object that provides information about available halls.
     * @param data The user input data, where data[1] is the file path.
     * @throws FileException if there is an issue opening the file.
     */
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        // data[1] = filePath
        try (FileReader fileReader = new FileReader(data[1])) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String eventName = scanner.nextLine();
                if (eventName.isEmpty() || eventName.isBlank()) {
                    continue;
                }
                String date = scanner.nextLine();
                LocalDateTime localDateTime = convertTime(date);
                // Hall info
                String hallNumber = scanner.nextLine();
                Hall hallByName = halls.getHallByName(hallNumber);
                Hall newHall = manualClone(hallByName);  // Clone the hall to avoid modifying the original
                Event event = new Event(localDateTime, eventName, newHall);
                eventBooker.addEvent(event);
            }
        } catch (IOException e) {
            throw new FileException("Something went wrong with opening the file");
        }
        System.out.println("Opened.");
    }

    /**
     * Converts the date string from the file into a `LocalDateTime` object.
     *
     * @param date The date string in the format "yyyy-MM-dd HH:mm:ss".
     * @return A `LocalDateTime` object representing the date and time.
     */
    private LocalDateTime convertTime(String date) {
        // date = "yyyy-MM-dd HH:mm:ss"
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * Manually clones a `Hall` object to create a new instance with the same properties,
     * but with an empty ticket list.
     *
     * @param hall The `Hall` object to be cloned.
     * @return A new `Hall` object with the same number, number of rows, and full status as the original,
     * but with an empty ticket list.
     */
    private Hall manualClone(Hall hall) {
        Hall hallCloned = new Hall(hall.getNumber(), hall.getNUMBER_OF_ROWS(), hall.isFull());
        hallCloned.setTickets(new ArrayList<>());  // Initialize with an empty ticket list
        return hallCloned;
    }
}
