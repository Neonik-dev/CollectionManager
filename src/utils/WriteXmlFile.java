package utils;

import exceptions.IncorrectDataEntryExceptions;
import collection.MusicBand;
import collection.MusicBandLinkedList;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * Writes to a xml file
 */
public class WriteXmlFile {
    private final String fileName = "output.xml";

    /**
     * Writes to a file using StAX
     */
    public void writeFile() throws XMLStreamException {
        XMLStreamWriter xmlWriter = null;

        try {
            CheckFile.checkFileForWrite(fileName);
            xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(new FileWriter(fileName));
            writeContent(xmlWriter);
        } catch (IncorrectDataEntryExceptions e) {
            System.out.println(e.getMessage());
        } catch (XMLStreamException | IOException | FactoryConfigurationError ex) {
            System.out.println("Произошла непредвиденная ошибка при сохранении коллекции в файл");
        } finally {
            if (xmlWriter != null) xmlWriter.close();
        }
        System.out.println("Сохранение в файл прошло успешно");
    }

    public void writeContent(XMLStreamWriter xmlWriter) throws XMLStreamException {
        // Открываем XML-документ и Пишем корневой элемент
        xmlWriter.writeStartDocument("1.0");
        xmlWriter.writeStartElement("ListMusicBand");
        for (MusicBand musicBand :  MusicBandLinkedList.getMusicBandLinkedList()) {
            xmlWriter.writeStartElement("MusicBand");

            // Заполняем все тэги для MusicBand
            xmlWriter.writeStartElement("id");
            xmlWriter.writeCharacters(Integer.toString(musicBand.getId()));
            xmlWriter.writeEndElement();

            xmlWriter.writeStartElement("name");
            xmlWriter.writeCharacters(musicBand.getName());
            xmlWriter.writeEndElement();

            xmlWriter.writeStartElement("coordinates");
            xmlWriter.writeStartElement("x");
            xmlWriter.writeCharacters(Double.toString(musicBand.getCoordinates().x()));
            xmlWriter.writeEndElement();
            xmlWriter.writeStartElement("y");
            xmlWriter.writeCharacters(Long.toString(musicBand.getCoordinates().y()));
            xmlWriter.writeEndElement();
            xmlWriter.writeEndElement();

            xmlWriter.writeStartElement("creationDate");
            xmlWriter.writeCharacters(musicBand.getCreationDate().toString());
            xmlWriter.writeEndElement();

            Optional<Long> participants = musicBand.getNumberOfParticipants();
            if (participants.isEmpty())
                xmlWriter.writeEmptyElement("numberOfParticipants");
            else {
                xmlWriter.writeStartElement("numberOfParticipants");
                xmlWriter.writeCharacters(Long.toString(participants.get()));
                xmlWriter.writeEndElement();
            }

            xmlWriter.writeStartElement("description");
            xmlWriter.writeCharacters(musicBand.getDescription());
            xmlWriter.writeEndElement();

            Optional<Date> date = musicBand.getEstablishmentDate();
            if (date.isEmpty())
                xmlWriter.writeEmptyElement("establishmentDate");
            else {
                xmlWriter.writeStartElement("establishmentDate");
                xmlWriter.writeCharacters(new SimpleDateFormat("dd.MM.yyyy").format(date.get()));
                xmlWriter.writeEndElement();
            }

            xmlWriter.writeStartElement("genre");
            xmlWriter.writeCharacters(musicBand.getGenre().toString());
            xmlWriter.writeEndElement();

            xmlWriter.writeStartElement("studio");
            Optional<String> address = musicBand.getStudio().getAddress();
            if (address.isEmpty()) {
                xmlWriter.writeEmptyElement("address");
            }
            else {
                xmlWriter.writeStartElement("address");
                xmlWriter.writeCharacters(address.get());
                xmlWriter.writeEndElement();
            }
            xmlWriter.writeEndElement();

            // Закрываем тэг MusicBand
            xmlWriter.writeEndElement();
        }
        // Закрываем корневой элемент
        xmlWriter.writeEndElement();
        // Закрываем XML-документ
        xmlWriter.writeEndDocument();
        xmlWriter.flush();
    }
}
