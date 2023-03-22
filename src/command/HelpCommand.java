package command;

import exceptions.IncorrectDataEntryExceptions;

import java.util.Map;

/**
 * Displays a description of all commands
 */
public class HelpCommand implements BaseCommand{
    private final String name = "help";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> выводит справку по доступным командам";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        Map<String, BaseCommand> commands = new Commands().getAllCommand();
        for (String key : commands.keySet()) {
            System.out.println(commands.get(key).getDescr());
        }
    }
}
