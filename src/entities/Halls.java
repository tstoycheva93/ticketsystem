package entities;

import exceptions.ThereIsNoSuchHall;

import java.util.HashMap;
import java.util.Map;

/**
 * The Halls class manages a collection of halls.
 * It provides methods for adding and retrieving halls by their name.
 */
public class Halls {
    private Map<String, Hall> hallMap;

    /**
     * Constructs a Halls instance with an empty map of halls.
     */
    public Halls() {
        this.hallMap = new HashMap<>();
    }

    /**
     * Retrieves a hall by its name.
     *
     * @param name The name of the hall.
     * @return The Hall object associated with the given name.
     * @throws RuntimeException If no hall with the given name is found.
     */
    public Hall getHallByName(String name) {
        if (hallMap.containsKey(name)) {
            return hallMap.get(name);
        }
        throw new ThereIsNoSuchHall("There is no such hall");
    }

    /**
     * Adds a hall to the collection.
     *
     * @param name The name of the hall.
     * @param hall The Hall object to be added.
     */
    public void addHall(String name, Hall hall) {
        hallMap.put(name, hall);
    }
}
