import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    File file;
    int lineNumber;
    boolean flagAlreadyHaveEmptyLine;

    WorkWithFile(File file, int lineNumber) {
        this.file = file;
        this.lineNumber = lineNumber;
    }
    public int getLineNumber() {
        return lineNumber;
    }
    public boolean checkFile() {
        return file.exists() && file.isFile();
    }

    public void fileToStringBuilder(StringBuilder stringBuilder) {
        try {
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        }
        catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public void PrintWithoutOption() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + file.getName() + ": " + e.getMessage());
        }
    }

    public int optionB(String line, StringBuilder content, int lineNumber) {
        if (!line.trim().isEmpty()) {
            content.append("\t").append(lineNumber++).append("\t");
        }
        content.append(line).append("\n");
        return lineNumber;
    }
    public void optionE(String line, StringBuilder content) {
        content.append(line).append("$\n");
    }
    public int optionN(String line, StringBuilder content, int lineNumber) {
        content.append("\t").append(lineNumber++).append("\t").append(line).append("\n");
        return lineNumber;
    }
    public void optionS(String line, StringBuilder content) {
        if (line.trim().isEmpty()) {
            if (!flagAlreadyHaveEmptyLine) {
                content.append("\n");
                flagAlreadyHaveEmptyLine = true;
            }
        } else {
            content.append(line).append(System.lineSeparator());
            flagAlreadyHaveEmptyLine = false;
        }
    }
    public void optionT(String line, StringBuilder content) {
        content.append(line.replace("\t", "^I")).append("\n");
    }
    public void print(String option) {
        if (option==null) {
            PrintWithoutOption();
            return;
        }

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                switch (option) {
                    case "-b":
                        lineNumber = optionB(line, content, lineNumber);
                        break;
                    case "-E":
                        optionE(line, content);
                        break;
                    case "-n":
                        lineNumber = optionN(line, content, lineNumber);
                        break;
                    case "-s":
                        optionS(line, content);
                        break;
                    case "-T":
                        optionT(line, content);
                        break;
                    case "-h":

                        break;
                    case "-v":

                        break;
                }
            }
        }
        catch (IOException e) {
            System.err.println("Ошибка при чтении файла " + file.getName() + ": " + e.getMessage());
        }
        System.out.println(content);
    }
}