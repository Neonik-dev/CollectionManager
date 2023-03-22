package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;
import utils.CheckID;

/**
 * Deletes an item from the collection by id
 */
public class RemoveByIdCommand implements BaseCommand{
    private final String name = "remove_by_id";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " id ->  удаляет элемент из коллекции по его id";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        int id = Integer.parseInt(args[0]);
        new MusicBandLinkedList().delete(id);
    }

    @Override
    public void checkArgs(String[] args) throws IncorrectDataEntryExceptions {
        CheckID.checkID(args);
    }
}
