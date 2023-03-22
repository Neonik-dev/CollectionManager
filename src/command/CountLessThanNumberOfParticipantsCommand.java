package command;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBandLinkedList;

public class CountLessThanNumberOfParticipantsCommand implements BaseCommand {
    private final String name = "count_less_than_number_of_participants";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " numberOfParticipants -> выводит количество элементов," +
                " значение поля numberOfParticipants которых меньше заданного";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions{
        checkArgs(args);
        long participants = Long.parseLong(args[0]);
        System.out.println(new MusicBandLinkedList().countNumberOfParticipants(participants) +
                " - столько музыкальных групп имеют количество участников меньше " + participants);
    }

    @Override
    public void checkArgs(String[] args) throws IncorrectDataEntryExceptions {
        if (args.length != 1)
            throw new IncorrectDataEntryExceptions("Необходимо ввести один числовой аргумент -> numberOfParticipants");

        try {
            long number = Long.parseLong(args[0]);
            if (number <= 0)
                throw new IncorrectDataEntryExceptions("Количество участников должно быть больше 0");
        } catch (NumberFormatException e) {
            throw new IncorrectDataEntryExceptions("Аргумент numberOfParticipants не является целым числом");
        }
    }
}
