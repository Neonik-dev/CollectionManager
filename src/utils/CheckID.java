package utils;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBand;

/**
 * Checks the collection for the presence of this id
 */
public class CheckID {
    /**
     * Checks the collection for the presence of this id
     * @param args Parameters that may contain id and other garbage
     * @throws IncorrectDataEntryExceptions If the id is not found in the collection or only garbage was passed instead of the id
     */
    public static void checkID(String[] args)  throws IncorrectDataEntryExceptions{
        if (args.length != 1)
            throw new IncorrectDataEntryExceptions("Необходимо ввести один числовой аргумент -> id");

        try {
            int id = Integer.parseInt(args[0]);
            if (!MusicBand.getAllId().contains(id))
                throw new IncorrectDataEntryExceptions("В коллекции нет элемента с таким id");
        } catch (NumberFormatException e) {
            throw new IncorrectDataEntryExceptions("Аргумент id не является целым числом");
        }
    }
}
