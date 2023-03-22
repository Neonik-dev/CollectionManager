package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;
import utils.History;
import utils.InputHandler;

/**
 * Terminates program execution, {@link InputHandler} sets the (exit = true) flag , clears all collections, closes all open threads
 */
public class ExitCommand implements BaseCommand{
    private final String name = "exit";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> завершает программу (без сохранения в файл)";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        InputHandler.exit = true;
        new MusicBandLinkedList().clear();
        new History().clear();
    }
}
