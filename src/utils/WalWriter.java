package utils;

import exceptions.IncorrectDataEntryExceptions;

import java.io.FileWriter;
import java.io.IOException;

public class WalWriter {
    public static final String fileName = "wal.txt";
    public static boolean executeFile = false;
    public static void openFile(String content) {
        try (FileWriter fileWriter = new FileWriter(fileName, !content.isEmpty())) {
            CheckFile.checkFileForWrite(fileName);
            contentWrite(fileWriter, content);
            fileWriter.flush();
        } catch (IncorrectDataEntryExceptions e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Возникли проблемы при записи с " + fileName + " файлом. Пожалуйста, проверьте его.");
        }
        // сохраняю старый сканер
        // открываю чтение
        // как то записываю в конец файла
        // закрываю соединение
        // передаю в старый файл

        // прописать ее вызов во всех нужных методах (add, remove_by_id, update_id, remove_head)
        // Если произошел вызов save, то затираю весь файл

        // Если файл wal.txt не пустой, то предложить пользователю востановление
        // Если соглашается, то вызываем execute_script и выполняем его
        // Если отказывается, то затираем файл
    }

    public static void contentWrite(FileWriter fileWriter, String content) throws IOException {
        if (!executeFile) {
            fileWriter.write(content);
        }
    }

    public static void clearFile() {
        openFile("");
    }
}
