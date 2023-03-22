package command;

import java.util.HashMap;
import java.util.Map;

/**
 * This class creates all valid commands in the Map structure, so the Command pattern is executed
 */
public class Commands {
    private static final Map<String, BaseCommand> COMMANDS = new HashMap<>();

    /**
     * All commands are initialized and written to the Map.
     * The constructor guarantees the creation of only one such variable.
     */
    public Commands() {
        if (COMMANDS.isEmpty()) {
            BaseCommand infoCommand = new InfoCommand();
            COMMANDS.put(infoCommand.getName(), infoCommand);

            BaseCommand helpCommand = new HelpCommand();
            COMMANDS.put(helpCommand.getName(), helpCommand);

            BaseCommand clearCommand = new ClearCommand();
            COMMANDS.put(clearCommand.getName(), clearCommand);

            BaseCommand historyCommand = new HistoryCommand();
            COMMANDS.put(historyCommand.getName(), historyCommand);

            BaseCommand addCommand = new AddCommand();
            COMMANDS.put(addCommand.getName(), addCommand);

            BaseCommand showCommand = new ShowCommand();
            COMMANDS.put(showCommand.getName(), showCommand);

            BaseCommand updateCommand = new UpdateCommand();
            COMMANDS.put(updateCommand.getName(), updateCommand);

            BaseCommand removeByIdCommand = new RemoveByIdCommand();
            COMMANDS.put(removeByIdCommand.getName(), removeByIdCommand);

            BaseCommand exitCommand = new ExitCommand();
            COMMANDS.put(exitCommand.getName(), exitCommand);

            BaseCommand removeHeadCommand = new RemoveHeadCommand();
            COMMANDS.put(removeHeadCommand.getName(), removeHeadCommand);

            BaseCommand printUniqueNumberOfParticipantsCommand = new PrintUniqueNumberOfParticipantsCommand();
            COMMANDS.put(printUniqueNumberOfParticipantsCommand.getName(), printUniqueNumberOfParticipantsCommand);

            BaseCommand countLessThanNumberOfParticipantsCommand = new CountLessThanNumberOfParticipantsCommand();
            COMMANDS.put(countLessThanNumberOfParticipantsCommand.getName(), countLessThanNumberOfParticipantsCommand);

            BaseCommand saveCommand = new SaveCommand();
            COMMANDS.put(saveCommand.getName(), saveCommand);

            BaseCommand executeScriptCommand = new ExecuteScriptCommand();
            COMMANDS.put(executeScriptCommand.getName(), executeScriptCommand);

            BaseCommand printFieldAscendingEstablishmentDateCommand = new PrintFieldAscendingEstablishmentDateCommand();
            COMMANDS.put(printFieldAscendingEstablishmentDateCommand.getName(), printFieldAscendingEstablishmentDateCommand);
        }
    }

    /**
     * @return We get a Map that stores the name of the team and the instance itself.
     */
    public Map<String, BaseCommand> getAllCommand() {
        return COMMANDS;
    }
}
