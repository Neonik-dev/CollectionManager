package command;

import exceptions.IncorrectDataEntryExceptions;

/**
 * All commands are inherited from this class.
 * Thus, it is possible to execute the pattern Command
 */
public interface BaseCommand {
    /**
     * @return command name that is expected to be received from the user
     */
    String getName();

    /**
     * @return description of the command
     */
    String getDescr();

    /**
     * Performs a command action. Often calls other methods at the same time
     * @param args Passed arguments
     * @throws IncorrectDataEntryExceptions If there are difficulties with the implementation
     */
    void execute(String[] args) throws IncorrectDataEntryExceptions;

    /**
     * Implements the validation of arguments that are passed to the command
     * @param args Passed arguments
     * @throws IncorrectDataEntryExceptions If the command arguments are not valid or the command does not have arguments
     */
     default void checkArgs(String[] args) throws IncorrectDataEntryExceptions {
         if (args.length != 0)
             throw new IncorrectDataEntryExceptions("Эта команда должна использоваться без аргументов");
     }
}
