public class SupportMethods {
    public boolean IsCommand(String string) {
        return string.startsWith("exit") || string.startsWith("$ cat") || string.startsWith("$ ls");
    }

    public String theBeginningOfTheLine(String string) {
        String[] line = string.split(" ");
        return line.length > 1 ? line[0] + ' ' + line[1] : line[0];
    }
}