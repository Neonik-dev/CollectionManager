package utils;

import exceptions.IncorrectDataEntryExceptions;
import exceptions.IncorrectDataEntryInFileExceptions;
import collection.MusicBandLinkedList;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Stack;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * This is a class that implements reading data from a xml file.
 * The main essence of the implementation of this class: we add each opening tag to the stack,
 * and when deleting, we check that the first element in the stack matches the tag we are deleting,
 * otherwise the file structure is incorrect.
 */
public class ReadXmlFile {
    private boolean flagExit = false;
    private static final String rootTag = "ListMusicBand", elementTag = "MusicBand";
    private final Stack<String> xmlTagFromFile = new Stack<>();
    private HashMap<String, String> coordinates;
    private HashMap<String, String> allTag;

    public ReadXmlFile() {
        clearData();
    }

    /**
     * This method runs line by line through each line of the file and executes different code,
     * depending on what is in front of it: an opening tag, a closing tag or the contents of the tag.
     *
     * @param fileName The path to the file to be read
     */
    public void readFile(String fileName) throws XMLStreamException {
        XMLStreamReader xmlReader = null;

        try {
            CheckFile.checkFileForRead(fileName);

            xmlReader = XMLInputFactory.newInstance()
                    .createXMLStreamReader(fileName, new BufferedInputStream(new FileInputStream(fileName)));

            read(xmlReader);
            System.out.println("Чтение файла прошло успешно");
        } catch (IncorrectDataEntryInFileExceptions | IncorrectDataEntryExceptions e) {
            System.out.println(e.getMessage());
            exit();
        } catch (FileNotFoundException e) {
            System.out.println("Файл с таким именем не найден!");
            exit();
        } catch (XMLStreamException | FactoryConfigurationError e) {
            System.out.println("Неверная структура xml файла");
            exit();
        } finally {
            if (xmlReader != null) xmlReader.close();
        }
    }

    public void read(XMLStreamReader xmlReader) throws XMLStreamException, IncorrectDataEntryInFileExceptions, IncorrectDataEntryExceptions {
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (flagExit && !(xmlReader.getEventType() == xmlReader.END_DOCUMENT)) {
                throw new IncorrectDataEntryInFileExceptions("Должен быть только один корневой тег");
            }

            if (xmlReader.isStartElement()) {
                addTag(xmlReader.getLocalName().trim());
//                    System.out.println(xmlReader.getLocalName().trim());
            } else if (xmlReader.isEndElement()) {
                removeTag(xmlReader.getLocalName());
//                    System.out.println("/" + xmlReader.getLocalName());
            } else if (xmlReader.hasText() && xmlReader.getText().trim().length() > 0) {
                addText(xmlReader.getText());
//                    System.out.println("   " + xmlReader.getText());
            }
        }
    }

    private void exit() {
        clearData();
        System.exit(0);
    }

    private void addText(String text) throws IncorrectDataEntryInFileExceptions {
        String lastTag = xmlTagFromFile.peek();
        if (allTag.containsKey(lastTag)) {
            checkMapNullValue(allTag, lastTag);
            allTag.put(lastTag, text);
        } else if (coordinates.containsKey(lastTag)) {
            checkMapNullValue(coordinates, lastTag);
            coordinates.put(lastTag, text);
        } else {
            checkMapNullValue(allTag, lastTag);
            allTag.put("studio", text);
        }
    }

    private void checkMapNullValue(HashMap<String, String> map, String tag) throws IncorrectDataEntryInFileExceptions {
        if (map.get(tag) != null)
            throw new IncorrectDataEntryInFileExceptions("Несколько раз встречается одно и то же поле (" + tag + ")");
    }

    private void removeTag(String tag) throws IncorrectDataEntryInFileExceptions, XMLStreamException {
        if (xmlTagFromFile.empty() || !xmlTagFromFile.peek().equals(tag)) {
            throw new XMLStreamException();
        }
        if (elementTag.equals(tag)) {
            new MusicBandLinkedList().add(allTag, coordinates);
            clearData();
        }
        xmlTagFromFile.pop();
        if (xmlTagFromFile.empty())
            flagExit = true;
    }

    private void addTag(String tag) throws IncorrectDataEntryInFileExceptions {
        if ((xmlTagFromFile.empty() && rootTag.equals(tag))) {
        } else if (xmlTagFromFile.peek().equals("studio")) {
            if (!tag.equals("address"))
                throw new IncorrectDataEntryInFileExceptions("В файле неверно указана студия");
        } else if (xmlTagFromFile.peek().equals("coordinates")) {
            if (!coordinates.containsKey(tag))
                throw new IncorrectDataEntryInFileExceptions("В файле неверно указаны координаты");
        } else if ((xmlTagFromFile.peek().equals(rootTag) && elementTag.equals((tag))) || (allTag.containsKey(tag))) {
        } else {
            throw new IncorrectDataEntryInFileExceptions("В файле указан несуществующий тег");
        }
        xmlTagFromFile.push(tag);
    }

    private void clearData() {
        allTag = new HashMap<>() {{
            put("id", null);
            put("name", null);
            put("creationDate", null);
            put("numberOfParticipants", null);
            put("coordinates", null);
            put("description", null);
            put("establishmentDate", null);
            put("genre", null);
            put("studio", null);
        }};
        coordinates = new HashMap<>() {{
            put("x", null);
            put("y", null);
        }};

    }
}
