package TheInterpreter.Commands.SpecificCommand;

import TheInterpreter.Commands.AbstractCommand;

import java.util.List;

public class CommandExit extends AbstractCommand {

    @Override
    public void execute(List<String> _keys, List<String> _args) throws Exception {
        throw new Exception("Завершение работы программы...");
    }


    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return "Введите exit, чтобы завершить работу программы.";
    }
}