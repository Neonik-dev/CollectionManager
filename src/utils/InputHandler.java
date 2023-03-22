package utils;

import command.BaseCommand;
import command.Commands;
import exceptions.IncorrectDataEntryExceptions;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * This class accepts input data from the user and processes it.
 * The user enters one of the commands,
 * then this class determines which class is responsible for its execution and passes data to it.
 */
public class InputHandler {
    /**
     * The field indicates exactly where the reading will take place,
     * from the file, System.in or something else. The code for everything is universal
     */
    public static Scanner scanner;
    private static final Map<String, BaseCommand> commands = new Commands().getAllCommand();
    public static String duplicateCommand = null;
    /**
     * A variable that makes it clear that you need to stop reading data
     */
    public static boolean exit = false;

    /**
     * Starts reading data from the stream System.in
     */
    public static void start() {
        scanner = new Scanner(System.in);
        run();
    }

    /**
     * Starts reading data from which is passed in the argument.
     * @param newScanner The source of the input data stream is passed
     */
    public static void start(Scanner newScanner) {
        scanner = newScanner;
        run();
    }

    private static void run() {
        System.out.println("Можете выполнить команду -> help , чтобы узнать все команды");
        String[] line;
        while (!exit && (duplicateCommand != null || scanner.hasNextLine())) {
            try {
                if (duplicateCommand != null) {
                    line = duplicateCommand.split(" ");
                    duplicateCommand = null;
                } else {
                    line = scanner.nextLine().split(" ");
                }
                BaseCommand command = commands.get(line[0]);
                if (command == null)
                    throw new NullPointerException();
                History.add(line[0]);
                command.execute(Arrays.copyOfRange(line, 1, line.length));
                System.out.println(line[0]);
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Напишите любую команду из списка. Чтобы посмотреть список команд воспользуйтесь командой -> help");
            } catch (IncorrectDataEntryExceptions e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
