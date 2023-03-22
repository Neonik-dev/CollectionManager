package exceptions;

/**
 * This class is called when invalid, incorrect data is received from the user. They are caught and this exception is thrown
 */
public class IncorrectDataEntryExceptions extends Exception{
    /**
     * Sends an error message
     * @param message error message
     */
    public IncorrectDataEntryExceptions(String message) {
        super(message);
    }
}
