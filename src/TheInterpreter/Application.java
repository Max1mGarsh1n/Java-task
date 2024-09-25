package TheInterpreter;

import TheInterpreter.Commands.AbstractCommand;
import TheInterpreter.Commands.ProcessingTheEnteredCommand.InputCommand;
import TheInterpreter.Commands.ProcessingTheEnteredCommand.Separator;
import TheInterpreter.Commands.SpecificCommand.CommandCat;
import TheInterpreter.Commands.SpecificCommand.CommandExit;
import TheInterpreter.Commands.SpecificCommand.CommandHelp;
import TheInterpreter.Commands.SpecificCommand.CommandLs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private final Map<String, AbstractCommand> mapOfCommands = new HashMap<>();
    private final String PREFIX = "$ ";

    public void fillMap() {
        mapOfCommands.put("cat", new CommandCat());
        mapOfCommands.put("ls", new CommandLs());
        mapOfCommands.put("help", new CommandHelp(mapOfCommands));
        mapOfCommands.put("exit", new CommandExit());
    }

    private void printPrefix() {
        System.out.print(PREFIX);
    }

    public void runApplication() {
        fillMap();
        boolean isExit = false;
        Scanner input = new Scanner(System.in);

        while (!isExit) {
            printPrefix();
            String string = input.nextLine();
            InputCommand inputCommand = Separator.separate(string);

            String commandKey = inputCommand.getCmd();
            AbstractCommand command = mapOfCommands.get(commandKey);

            if (command == null) {
                System.out.println("Команда не найдена: " + commandKey);
            } else {
                try {
                    command.execute(inputCommand.getKeys(), inputCommand.getArgs());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    isExit = true;
                }
            }
        }
    }
}