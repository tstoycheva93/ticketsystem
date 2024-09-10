package operations.interfaces;

import entities.EventBooker;
import entities.Halls;

/**
 * The {@code Operation} interface represents an operation that can be performed
 * with the {@code EventBooker} and {@code Halls}.
 * <p>
 * Implementations of this interface define specific operations that manipulate
 * the event data and hall information, based on the provided input data.
 * </p>
 */
public interface Operation {
    /**
     * Processes the operation with the given {@code EventBooker}, {@code Halls},
     * and additional data.
     *
     * @param eventBooker the {@code EventBooker} instance to be used in the operation
     * @param halls the {@code Halls} instance to be used in the operation
     * @param data an array of {@code String} containing the data required for the operation
     */
    void process(EventBooker eventBooker, Halls halls, String[] data);
}
