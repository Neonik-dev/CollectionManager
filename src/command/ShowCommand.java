package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;

/**
 * The command outputs all the elements of the collection and their field values
 */
public class ShowCommand implements BaseCommand{
    private final String name = "show";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> выводит все элементы коллекции";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        new MusicBandLinkedList().show();
    }
}
