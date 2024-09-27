package theInterpreter.commands.specificCommand;

import theInterpreter.commands.AbstractCommand;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CommandLs extends AbstractCommand {
    private List<String> keys = new ArrayList<>();
    private List<String> args = new ArrayList<>();

    public CommandLs() {
        supportedKeys.put("-r", "обратный порядок сортировки");
        supportedKeys.put("-X", "сортировать по алфавиту");
    }

    @Override
    public void execute(List<String> _keys, List<String> _args) throws Exception {
        keys = _keys;
        args = _args;
        if (!checkEnteredKeysAreSupported(keys)) {
            throw new Exception("Введены неподдерживаемые ключи!");
        } else {
            if (args.isEmpty()) {
                executeWithoutArgs();
            } else {
                executeWithArgs();
            }
        }
    }
    public void executeWithoutArgs() {
        File dir = new File(".");
        Arrays.stream(dir.listFiles())
                .filter(File::isFile)
                .map(File::getName)
                .forEach(System.out::println);
    }

    public void executeWithArgs() {
        for (String arg : args) {
            File dir = new File(arg);
            if (dir.exists() && dir.isDirectory()) {
                File[] filesList = dir.listFiles();
                if (filesList != null) {
                    processFiles(filesList);
                } else {
                    System.out.println("Ошибка: Не удалось получить список файлов в директории " + arg);
                }
            } else {
                System.out.println("Ошибка: Директория " + arg + " не существует или не является директорией.");
            }
        }
    }

    private void processFiles(File[] files) {
        if (keys.contains("-X")) {
            sortByName(files);
        }

        if (keys.contains("-r")) {
            Arrays.sort(files, Collections.reverseOrder());
        }

        Arrays.stream(files)
                .filter(File::isFile)
                .map(File::getName)
                .forEach(System.out::println);
    }

    @Override
    public String getName() {
        return "ls";
    }

    @Override
    public String getDescription() {
        return "Если вы часто используете терминал, то довольно часто возникает необходимость посмотреть содержимое той или иной папки.\n" +
                "Автодополнение спасает во многих случаях, но если вы не знаете что искать и не хотите использовать файловый менеджер, " +
                "\nто быстро посмотреть что находится в папке может быть очень полезно.\n" +
                "Другое применение команды ls - посмотреть разрешения для файлов и папок.";
    }

    private void sortByName(File[] files) {
        Arrays.sort(files, (f1, f2) -> f1.getName().compareTo(f2.getName()));
    }
}