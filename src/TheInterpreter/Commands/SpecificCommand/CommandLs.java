package TheInterpreter.Commands.SpecificCommand;

import TheInterpreter.Commands.AbstractCommand;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CommandLs extends AbstractCommand {
    private List<String> keys = new ArrayList<>();
    private List<String> args = new ArrayList<>();

    public CommandLs() {
        supportedKeys.put("-S", "описание ключа -S");
        supportedKeys.put("-X", "описание ключа -X");
    }

    @Override
    public void execute(List<String> _keys, List<String> _args) throws Exception {
        keys = _keys;
        args = _args;
        if (checkEnteredKeysAreSupported(keys)) {
            throw new Exception("Введены неподдерживаемые ключи!");
        } else {
            System.out.println("ls что-то делает");

        }
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

    private void sortBySize(File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                return Long.compare(f1.length(), f2.length());
            }
        });
    }

    private void sortByName(File[] files) {
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                return f1.getName().compareTo(f2.getName());
            }
        });
    }
}