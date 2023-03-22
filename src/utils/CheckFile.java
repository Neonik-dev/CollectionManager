package utils;

import exceptions.IncorrectDataEntryExceptions;

import java.io.File;

/**
 * Various checks for working with the file
 */
public class CheckFile {
    /**
     * Checks whether such a file exists, whether it is a file and whether it is possible to read it.
     * @param fileName file path
     * @throws IncorrectDataEntryExceptions If the path does not point to a file, or it cannot be read, an exception will be thrown that the file name is incorrect
     */
    public static void checkFileForRead(String fileName) throws IncorrectDataEntryExceptions {
        File file = new File(fileName);
        if (!file.isFile())
            throw new IncorrectDataEntryExceptions("Путь " + fileName + " указывает не на файл");
        if (!file.canRead())
            throw new IncorrectDataEntryExceptions("Файл невозможно прочитать, недостаточно прав");
    }

    /**
     * A check is performed on the file, if it already exists, is it exactly a file and can I write to it?
     * @param fileName the path to the file
     * @throws IncorrectDataEntryExceptions If the path is correct, but there is not a file, but a directory or a file that has write permissions disabled
     */
    public static void checkFileForWrite(String fileName) throws IncorrectDataEntryExceptions{
        File file = new File(fileName);
        if (file.exists()) {
            if (!file.isFile())
                throw new IncorrectDataEntryExceptions("Путь указывает не на файл");
            if (!file.canWrite()) {
                throw new IncorrectDataEntryExceptions("В файл невозможно произвести запись, недостаточно прав");
            }
        }
    }
}
