package theInterpreter.commands.specificCommand;

import theInterpreter.commands.AbstractCommand;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CommandCat extends AbstractCommand {
    private List<String> keys = new ArrayList<>();
    private List<String> args = new ArrayList<>();

    public CommandCat() {
        supportedKeys.put("-n", "нумерация всех строк");
        supportedKeys.put("-b", "нумерация непустых строк");
        supportedKeys.put("-E", "добавление знака $ в конец строки");
    }

    private boolean checkValidMoreThanSign() throws Exception {
        int moreThanIndex = args.indexOf(">");
        if (moreThanIndex == -1) {
            return false;
        }
        if (moreThanIndex != args.size() - 2 || Collections.frequency(args, ">") > 1) {
            throw new Exception("Знак '>' должен отсутствовать или стоять предпоследним, но встречаться только один раз");
        }
        return true;
    }

    public void executeWithoutArgs() {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        System.out.println(line);
    }

    private String modificateLineWithKeyE(String line) {
        return line.concat("$");
    }

    private String modificateLineWithKeyN(String line, int lineNumber) {
        return lineNumber + " " + line;
    }

    private String modificateLineWithKeyB(String line, int lineNumber) {
        return !line.isEmpty() ? lineNumber + " " + line : line;
    }

    private String modificateLine(String line, int lineNumber) throws Exception {
        if (keys.contains("-n") && keys.contains("-b")) {
            throw new Exception("В команде не могут быть одновременно ключи '-b' и '-n'");
        }

        for (String key : keys) {
            switch (key) {
                case "-E":
                    line = modificateLineWithKeyE(line);
                    break;
                case "-n":
                    line = modificateLineWithKeyN(line, lineNumber);
                    break;
                case "-b":
                    line = modificateLineWithKeyB(line, lineNumber);
                    break;
            }
        }
        return line;
    }

    public void printArgsWithKeys(int size, PrintWriter output) {
        StringBuilder stringBuilder = new StringBuilder();
        int lineNumber = 1;

        for (int i = 0; i < size; ++i) {
            File file = new File(args.get(i) + ".txt");
            try {
                List<String> lines = Files.readAllLines(file.toPath());

                for (String line : lines) {
                    String modifiedLine = modificateLine(line, lineNumber);
                    stringBuilder.append(modifiedLine).append(System.lineSeparator());
                    ++lineNumber;
                }
            } catch (Exception e) {
                e.getMessage();
            }

        }
        output.println(stringBuilder.toString());
        output.flush();
    }

    @Override
    public void execute(List<String> _keys, List<String> _args) throws Exception {
        keys = _keys;
        args = _args;

        if (!checkEnteredKeysAreSupported(keys)) {
            throw new Exception("Введены неподдерживаемые ключи!");
        }

        if (args.isEmpty()) {
            executeWithoutArgs();
        } else {
            boolean isValidMoreThanSign = checkValidMoreThanSign();
            PrintWriter receiverFile = isValidMoreThanSign
                    ? new PrintWriter(args.getLast() + ".txt")
                    : new PrintWriter(System.out);
            printArgsWithKeys(isValidMoreThanSign ? args.size() - 2 : args.size(), receiverFile);
        }
    }

    @Override
    public String getName() {
        return "cat";
    }

    @Override
    public String getDescription() {
        return "Название команды - это сокращения от слова catenate. По сути, задача команды cat очень проста - она читает данные из файла или стандартного ввода и выводит их на экран. " +
                "\nЭто все, чем занимается утилита. " +
                "\nНо с помощью ее опций и операторов перенаправления вывода можно сделать очень многое";
    }
}
