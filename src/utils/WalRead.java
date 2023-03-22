package utils;

import command.ExecuteScriptCommand;
import exceptions.IncorrectDataEntryExceptions;

import java.io.*;
import java.util.Scanner;

public class WalRead {
    public static void checkFile() {
        String fileName = WalWriter.fileName;
        try {
            CheckFile.checkFileForRead(fileName);
        } catch (IncorrectDataEntryExceptions e) {
            System.out.println(e.getMessage());
        }

        if (new File(fileName).length() != 0L) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Прошлые изменения были не сохранены. Применить их? (Y/n) ");
                String userLine = scanner.nextLine().strip();
                if(userLine.equalsIgnoreCase("y")) {
                    executeFile(fileName);
                    break;
                } else if(userLine.equalsIgnoreCase("n")) {
                    break;
                } else {
                    System.out.println("Неверный ввод!");
                }

            }
        } else {
            System.out.println("В файле актуальные данные");
        }
    }

    public static void executeFile(String fileName) {
        try {
            WalWriter.executeFile = true;
            new ExecuteScriptCommand().execute(new String[]{fileName});
            WalWriter.executeFile = false;
        } catch (IncorrectDataEntryExceptions e) {
            System.out.println(fileName + " удалили, не удалось выполнить изменения");
        }
        System.out.println("Все изменения выполнились успешно, теперь данные акуальны");
    }

//    public void Filee() {
//        String fileName = WalWriter.fileName;
//        try (BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(fileName))) {
//            CheckFile.checkFileForRead(fileName);
//            if (fileReader.)
//        } catch (IncorrectDataEntryExceptions e) {
//            System.out.println(e.getMessage());
//        } catch (IOException e) {
//            System.out.println("Возникли проблемы при записи с " + fileName + " файлом. Пожалуйста, проверьте его.");
//        }
//    }
}
