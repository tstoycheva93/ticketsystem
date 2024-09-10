package operations.baseOperation;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

/**
 * The {@code Exit} class implements the {@code Operation} interface
 * and defines the behavior for exiting the program.
 * This operation outputs a message indicating that the program is exiting.
 */
public class Exit implements Operation {
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        System.out.println(
                "\nBye...");
        System.exit(0);    }
}
