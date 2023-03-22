package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBand;
import collection.MusicBandLinkedList;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Displays all establishmentDate in sorted order
 */
public class PrintFieldAscendingEstablishmentDateCommand implements BaseCommand{
    private final String name = "print_field_ascending_establishment_date";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> выводит значения поля establishmentDate всех элементов в порядке возрастания";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        Comparator<MusicBand> comparator = (o1, o2) -> {
            if (o1.getEstablishmentDate().isEmpty() || o2.getEstablishmentDate().isEmpty())
                return 0;
            return o1.getEstablishmentDate().get().compareTo(o2.getEstablishmentDate().get());
        };

        LinkedList<MusicBand> myCollection =  MusicBandLinkedList.getMusicBandLinkedList();
        myCollection.sort(comparator);
        for(MusicBand musicBand : myCollection) {
            Optional<Date> establishmentDate = musicBand.getEstablishmentDate();
            System.out.println(musicBand.getEstablishmentDate().isPresent() ? establishmentDate.get() : null);
        }
    }
}
