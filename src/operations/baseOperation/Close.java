package operations.baseOperation;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The {@code Close} class implements the {@code Operation} interface
 * and defines the behavior for closing a file.
 * This operation outputs a message indicating that the file is being closed.
 */
public class Close implements Operation {
    FileReader fileReader = null;
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        try {
            fileReader = new FileReader(data[1]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Closing " + data[1]);
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
