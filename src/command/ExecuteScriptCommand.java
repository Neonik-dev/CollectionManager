package command;

import exceptions.IncorrectDataEntryExceptions;
import utils.CheckFile;
import utils.InputHandler;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The command switches the input data stream to the specified file.
 * Now the reading will take place from there
 */
public class ExecuteScriptCommand implements BaseCommand {
    private final String name = "execute_script";
    private static int recursionDepth = 0;
    private static int MaxRecursionDepth = 0;
    private final static HashSet<String> fixRecursion = new HashSet<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> очищает коллекцию";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        if (fixRecursion.contains(args[0])) {
            recursionDepth += 1;
            if (MaxRecursionDepth == 0)
                InputMaxRecursion();

            if (recursionDepth > MaxRecursionDepth) {
                recursionDepth--;
                throw new IncorrectDataEntryExceptions("Глубина рекурсии больше допустимой");
            }

            try {
                Scanner keepOldScanner = InputHandler.scanner;
                Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
                InputHandler.start(scanner);
                InputHandler.scanner = keepOldScanner;
            } catch (FileNotFoundException e) {
                throw new IncorrectDataEntryExceptions("Указанный файл не удалось прочитать :(");
            }
            recursionDepth--;
        } else {
            try {
                Scanner keepOldScanner = InputHandler.scanner;
                fixRecursion.add(args[0]);
                Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(args[0])));
                InputHandler.start(scanner);
                InputHandler.scanner = keepOldScanner;
                fixRecursion.remove(args[0]);
            } catch (FileNotFoundException e) {
                throw new IncorrectDataEntryExceptions("Указанный файл не удалось прочитать :(");
            }
        }
    }

    public void InputMaxRecursion() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Произошла рекурсия, какая максимально допустимая глубина: ");
                MaxRecursionDepth = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Нужно ввести число");
            }
        }
    }

    @Override
    public void checkArgs(String[] args) throws IncorrectDataEntryExceptions {
        if (args.length != 1)
            throw new IncorrectDataEntryExceptions("Необходимо ввести один аргумент -> file_name");

        CheckFile.checkFileForRead(args[0]);
    }
}
