package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;

/**
 * Adds a new item to the collection
 */
public class AddCommand implements BaseCommand{
    private final String name = "add";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> добавляет новый элемент в коллекцию";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        new MusicBandLinkedList().add();
    }

}
