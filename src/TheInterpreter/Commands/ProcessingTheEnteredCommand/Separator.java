package TheInterpreter.Commands.ProcessingTheEnteredCommand;

import java.util.ArrayList;
import java.util.List;

public class Separator {

    public static InputCommand separate(String command)
    {
        String[] parts = command.split(" +");
        String cmd = parts[0];
        List<String> keys = new ArrayList<>();
        List<String> args = new ArrayList<>();

        for (int i = 1; i < parts.length; ++i) {
            if (parts[i].startsWith("-")) {
                keys.add(parts[i]);
            } else {
                args.add(parts[i]);
            }
        }

        return new InputCommand(cmd, keys, args);
    }
}