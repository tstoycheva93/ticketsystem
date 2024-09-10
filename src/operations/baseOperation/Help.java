package operations.baseOperation;

import entities.EventBooker;
import entities.Halls;
import operations.interfaces.Operation;

/**
 * The {@code Help} class implements the {@code Operation} interface
 * and defines the behavior for displaying help information about available commands.
 * This operation outputs a formatted help message listing all available commands.
 */
public class Help implements Operation {
    @Override
    public void process(EventBooker eventBooker, Halls halls, String[] data) {
        String builder =
                "\nhelp\t>\tsee available commands\n" +
                        "\t___________________________________\n" +
                        "open\t>\topen <file.txt>\n" +
                        "close\t>\tclose currently open file\n" +
                        "save\t>\tsave currently open file as <file.txt>\n" +
                        "saveas\t>\tsave currently open file as <file2.txt>\n" +
                        "\t___________________________________\n" +
                        "addevent\t>\tadd event on <date> in <hall> with <name>\n" +
                        "freeseats\t>\tcheck free seats on <date> for <name>\n" +
                        "book\t\t>\tbook <row> <seat> on <date> for <name> with <note>\n" +
                        "unbook\t\t>\tunbook <row> <seat> on <date> for <name>\n" +
                        "buy\t\t>\tbuy <row> <seat> on <date> for <name>\n" +
                        "bookings\t>\tsee bookings for [<name>] [<date>]\n" +
                        "check\t\t>\tcheck <code> validity\n" +
                        "report\t\t>\tsee purchases for events <from> <to> in [<hall>]\n" +
                        "mostfamous\t>\tsee best selling events\n" +
                        "statistic\t>\tsee worst selling events\n" +
                        "\t___________________________________\n" +
                        "exit\t\t>\texit the program";
        System.out.println(builder);
    }
}
