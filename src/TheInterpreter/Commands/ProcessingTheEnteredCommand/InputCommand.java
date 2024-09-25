package TheInterpreter.Commands.ProcessingTheEnteredCommand;

import java.util.List;

public class InputCommand {
    private final String cmd;
    private final List<String> keys;
    private final List<String> args;

    InputCommand(String _cmd, List<String> _keys, List<String> _args) {
        cmd = _cmd;
        keys = _keys;
        args = _args;
    }
    public String getCmd() {
        return cmd;
    }

    public List<String> getKeys() {
        return keys;
    }

    public List<String> getArgs() {
        return args;
    }
}