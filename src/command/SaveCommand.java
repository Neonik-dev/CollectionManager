package command;

import exceptions.IncorrectDataEntryExceptions;
import utils.WalWriter;
import utils.WriteXmlFile;

import javax.xml.stream.XMLStreamException;

/**
 * Implements saving a collection to a file
 */
public class SaveCommand implements BaseCommand {
    private final String name = "save";
    private final WriteXmlFile writer = new WriteXmlFile();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Команда " + name + " -> сохраняет коллекцию в файл";
    }

    @Override
    public void execute(String[] args) throws IncorrectDataEntryExceptions {
        checkArgs(args);
        try {
            writer.writeFile();
            WalWriter.clearFile();
        } catch (XMLStreamException e) {
            System.out.println("Не удалось закрыть соединение с файлом");
        }
    }
}
