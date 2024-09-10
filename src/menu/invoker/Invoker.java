package menu.invoker;

import entities.EventBooker;
import entities.Hall;
import entities.Halls;
import menu.baseCommands.*;
import menu.commands.*;
import operations.baseOperation.*;
import operations.mainOperations.*;
import utils.CommandNames;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * The Invoker class manages and executes commands within the application.
 * It maintains a map of command names to command instances and handles user input to trigger the appropriate commands.
 */
public class Invoker {
    private Map<CommandNames, Commands> commandsMap;
    private EventBooker eventBooker;
    private Halls halls;

    /**
     * Constructs an Invoker instance, initializes the EventBooker and Halls, and prepares the command map.
     */
    public Invoker() {
        this.eventBooker = new EventBooker();
        this.halls = new Halls();
        this.commandsMap = new HashMap<>();
    }

    /**
     * Starts the command invocation loop.
     * Continuously reads user input from the console, parses commands, and executes the corresponding operations.
     * The loop terminates when the user inputs "exit" or "close".
     */
    public void invoke() {
        fillMap();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] data = command.split("\\s++");
            if (commandsMap.containsKey(CommandNames.getByCode(data[0]))) {
                try {
                    commandsMap.get(CommandNames.getByCode(data[0])).execute(eventBooker, halls, data);
                }catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Invalid command");
            }
        }
    }

    /**
     * Initializes the command map with command names and their corresponding command instances.
     * Also adds predefined halls to the Halls instance.
     */
    private void fillMap() {
        // specific commands
        commandsMap.put(CommandNames.ADD_EVENT_COMMAND, new AddEventCommand(new AddEvent()));
        commandsMap.put(CommandNames.BOOKING_COMMAND, new BookingCommand(new Booking()));
        commandsMap.put(CommandNames.BOOK_SEAT_COMMAND, new BookSeatCommand(new BookSeat()));
        commandsMap.put(CommandNames.BUY_TICKET_COMMAND, new BuyTicketCommand(new BuyTicket()));
        commandsMap.put(CommandNames.CHECK_COMMAND, new CheckCommand(new Check()));
        commandsMap.put(CommandNames.FREE_SEATS_COMMAND, new FreeSeatsCommand(new FreeSeats()));
        commandsMap.put(CommandNames.MOST_FAMOUS_COMMAND, new MostFamousCommand(new MostFamous()));
        commandsMap.put(CommandNames.REPORT_COMMAND, new ReportCommand(new Report()));
        commandsMap.put(CommandNames.STATISTIC_FOR_TEN_PERCENT_COMMAND, new StatisticForTenPercentCommand(new StatisticForTenPercent()));
        commandsMap.put(CommandNames.UNBOOK_SEAT_COMMAND, new UnbookSeatCommand(new UnbookSeat()));
        // base commands
        commandsMap.put(CommandNames.OPEN, new OpenCommand(new Open()));
        commandsMap.put(CommandNames.CLOSE, new CloseCommand(new Close()));
        commandsMap.put(CommandNames.SAVE, new SaveCommand(new Save()));
        commandsMap.put(CommandNames.SAVEAS, new SaveAsCommand(new SaveAs()));
        commandsMap.put(CommandNames.HELP, new HelpCommand(new Help()));
        commandsMap.put(CommandNames.EXIT, new ExitCommand(new Exit()));

        halls.addHall("Hall-1", new Hall("1", 5, false));
        halls.addHall("Hall-2", new Hall("2", 5, false));
        halls.addHall("Hall-3", new Hall("3", 5, false));
        halls.addHall("Hall-4", new Hall("4", 10, false));
        halls.addHall("Hall-5", new Hall("5", 1, false));
    }
}
