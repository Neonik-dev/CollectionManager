package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;
import utils.CheckID;

/**
 * This class updates the value of a collection item by id if it finds such an item by id
 */
public class UpdateCommand implements BaseCommand{
    private final String name = "update";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " id -> обновляет значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        int id = Integer.parseInt(args[0]);
        new MusicBandLinkedList().update(id);
    }

    @Override
    public void checkArgs(String[] args) throws IncorrectDataEntryExceptions {
        CheckID.checkID(args);
    }
}
