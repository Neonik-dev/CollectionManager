package command;

import exceptions.IncorrectDataEntryExceptions;
import utils.History;

/**
 * Stores the history of the last 13 commands
 */
public class HistoryCommand implements BaseCommand{
    private final String name = "history";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> выводит последние 13 команд (без их аргументов)";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        System.out.println(History.printHistory());
    }
}
