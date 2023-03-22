import utils.InputHandler;
import utils.ReadXmlFile;
import utils.WalRead;

import javax.xml.stream.XMLStreamException;


public class Main {
    public static void main(String[] args) {
        try {
//            new ReadXmlFile().readFile(System.getenv().get("INPUT_FILE"));
            new ReadXmlFile().readFile("MusicBand.xml");
            WalRead.checkFile();
            InputHandler.start();
        } catch (XMLStreamException e) {
            System.out.println("Не удалось закрыть соединение с файлом");
        } catch (NullPointerException e) {
            System.out.println("Имя входного файла, нет в переменной окружения");
        }
    }
}
