package TheInterpreter.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractCommand {
    protected Map<String, String> supportedKeys = new HashMap<>();

    public abstract void execute(List<String> _keys, List<String> _args) throws Exception;

    public abstract String getName();
    public abstract String getDescription();

    public boolean checkEnteredKeysAreSupported(List<String> keys) {
        return supportedKeys.keySet().containsAll(keys);
    }


    public void printSupportedKeys() {
        supportedKeys.entrySet()
                .stream()
                .forEach(entry -> System.out.println("Ключ: " + entry.getKey() + ", описание: " + entry.getValue()));
    }
    @Override
    public String toString() {
        return getName() + "\n" + getDescription();
    }
}