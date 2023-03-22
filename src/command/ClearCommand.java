package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;

/**
 * Completely cleans up the collection without writing to a file
 */
public class ClearCommand implements BaseCommand{
    private final String name = "clear";
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
        new MusicBandLinkedList().clear();
    }

}
