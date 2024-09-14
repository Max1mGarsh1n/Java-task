import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class CommandCat {
    private final String command;
    private String option = null;
    private File receiver_file = null;
    private StringBuilder contentToWrite = new StringBuilder();

    CommandCat (String string) { command = string; }
    public boolean CommandIsGood() {
        int greaterThanCount = 0;
        String[] parts = command.split(" ");

        for (String str : parts) {
            greaterThanCount += str.equals(">") ? 1 : 0;
        }

        return greaterThanCount<=1;
    }
    public boolean isOption(String string) {
        String[] options = { "-b", "-E", "-n", "-s", "-T", "-h", "-v"};
        return Arrays.asList(options).contains(string);
    }

    public void parseCommand() {
        String[] parts = command.split(" ");


        if (parts.length > 2) {
            WorkWithFile file = new WorkWithFile(new File(parts[2] + ".txt"));

            if (isOption(parts[2])) {
                option = parts[2];
            } else if (!file.checkFile() && !parts[2].equals("-")) {
                System.out.println("Опция написана неправильно или отсутствует такой файл-приёмник!");
                return;
            }
        }

        for (int i = option==null ? 2 : 3; i < parts.length; ++i) {
            if (Objects.equals(parts[i], "-")) {
                Scanner input = new Scanner(System.in);
                String userInput = input.nextLine();
                System.out.println(userInput);
                contentToWrite.append(userInput).append("\n");
            } else if (Objects.equals(parts[i], ">")) {
                if (receiver_file == null) {
                    receiver_file = new File(parts[++i] + ".txt");
                } else {
                    System.out.println("Пользователь не может использовать вывод в файл дважды!");
                    return;
                }
            } else {
                File source_file = new File(parts[i] + ".txt");
                WorkWithFile workWithFile = new WorkWithFile(source_file);
                if (workWithFile.checkFile()) {
                    workWithFile.print(option);
                    workWithFile.fileToStringBuilder(contentToWrite);
                } else {
                    System.out.println("НЕТ ТАКОГО ФАЙЛА РЕСУРСОВ!");
                    return;
                }
            }

        }
        if (receiver_file != null) {
            try (FileWriter writer = new FileWriter(receiver_file)) {
                writer.write(contentToWrite.toString());
            } catch (IOException e) {
                System.out.println("Ошибка записи в файл-приёмник: " + e.getMessage());
            }
        }
    }
}