package theInterpreter.commands.specificCommand;

import theInterpreter.commands.AbstractCommand;
import java.util.*;

public class CommandHelp extends AbstractCommand {
    private final Map<String, AbstractCommand> mapOfCommands;
    private List<String> args = new ArrayList<>();

    public CommandHelp(Map<String, AbstractCommand> map) {
        mapOfCommands = map;
    }

    private boolean checkTheExistenceOfCommands(List<String> commands) {
        return mapOfCommands.keySet().containsAll(commands);
    }

    @Override
    public void execute(List<String> _keys, List<String> _args) {
        args = _args;

        if (args.isEmpty()) {
            printCommands(mapOfCommands.keySet());
        } else {
            if (!checkTheExistenceOfCommands(args)) {
                System.out.println("Введённые команды не поддерживаются!");
            } else {
                printCommands(args);
            }
        }
    }

    private void printCommands(Collection<String> collection) {
        collection.stream()
                .map(mapOfCommands::get)
                .forEach(command -> {
                    System.out.println(command);
                    command.printSupportedKeys();
                    System.out.println();
                });
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "При использовании команды 'help' выводится название команды, её ключи и описание.\n" +
                "Можно ввести одну или несколько команд, чтобы просмотреть информацию только о них.";
    }
}