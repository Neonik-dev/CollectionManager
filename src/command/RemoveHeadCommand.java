package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;

/**
 * Deletes the first element of the collection
 */
public class RemoveHeadCommand implements BaseCommand {
    private final String name = "remove_head";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> выводит и удаляет первый элемент коллекции";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        new MusicBandLinkedList().delete();
    }
}
