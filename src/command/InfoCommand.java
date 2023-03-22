package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBand;
import collection.MusicBandLinkedList;

import java.util.LinkedList;

/**
 * Displays some information about the collection
 */
public class InfoCommand implements BaseCommand {
    private final String name = "info";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> выводит информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        LinkedList<MusicBand> myCollections = MusicBandLinkedList.getMusicBandLinkedList();
        System.out.println("Тип коллекции -> " + myCollections.getClass().getName());
        System.out.println("Дата инициализации коллекции -> " + MusicBandLinkedList.getDateCreated());
        System.out.println("Колличество элементов в коллекции -> " + myCollections.size());
    }
}
