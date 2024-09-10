package operations.baseOperation;

import entities.Event;
import entities.EventBooker;
import entities.Hall;
import entities.Halls;
import exceptions.FileException;
import operations.interfaces.Operation;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The {@code Save} class implements the {@code Operation} interface
 * and defines the behavior for saving event data to a file.
 * This operation writes event details from the {@code EventBooker} to the specified file.
 */
public class Save implements Operation {
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        try(FileWriter fileWriter=new FileWriter(data[1],false)){
            List<Event> events = eventBooker.getEvents();
            for (int i = 0; i < events.size(); i++) {
                Event event = events.get(i);
                String name = event.getName();
                LocalDateTime date = event.getDate();
                String time=convertDateToString(date);
                Hall hall = event.getHall();
                String number = "Hall-"+hall.getNumber();
                fileWriter.write(name);
                fileWriter.write("\n");
                fileWriter.write(time);
                fileWriter.write("\n");
                fileWriter.write(number);
                if(i<events.size()-1){
                    fileWriter.write("\n");
                    fileWriter.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new FileException("An error occurred while writing file");
        }
    }

    private String convertDateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
