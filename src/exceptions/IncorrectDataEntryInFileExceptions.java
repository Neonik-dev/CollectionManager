package exceptions;

/**
 * This class is called when the program has caught an error when reading in a xml file.
 * Most likely there are problems with the file structure or incorrect filling of the collection with elements.
 */
public class IncorrectDataEntryInFileExceptions extends Exception{
    /**
     * Sends an error message
     * @param message error message
     */
    public IncorrectDataEntryInFileExceptions(String message) {
        super(message);
    }
}

