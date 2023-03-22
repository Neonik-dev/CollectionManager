package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;

/**
 * Outputs all unique values numberOfParticipants
 */
public class PrintUniqueNumberOfParticipantsCommand implements BaseCommand{
    private final String name = "print_unique_number_of_participants";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> выводит уникальные значения поля numberOfParticipants всех элементов в коллекции";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        new MusicBandLinkedList().UniqueNumberOfParticipants();
    }
}
