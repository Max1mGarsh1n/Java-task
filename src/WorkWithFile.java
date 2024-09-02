import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile
{
    File file;

    WorkWithFile(File file) { this.file = file; }
    public boolean FileIsGood()
    {
        return file.exists() && file.isFile();
    }

    public void FileToStringBuilder(StringBuilder stringBuilder) {
        try
        {
            List<String> lines = Files.readAllLines(file.toPath());
            for (String line : lines)
                stringBuilder.append(line).append(System.lineSeparator());

        }
        catch (IOException e)
        {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public void PrintWithoutOption()
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        }
        catch (IOException e)
        {
            System.err.println("Ошибка при чтении файла " + file.getName() + ": " + e.getMessage());
        }
    }
    public void Print(String option)
    {
        if (option==null)
        {
            PrintWithoutOption();
            return;
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            int lineNumber = 1;
            boolean flagAlreadyHaveEmptyLine = false;

            while ((line = reader.readLine()) != null)
            {
                switch (option)
                {
                    case "-b":
                        if (!line.trim().isEmpty())
                            content.append("\t").append(lineNumber++).append("\t");
                        content.append(line).append("\n");
                        break;
                    case "-E":
                        content.append(line).append("$\n");
                        break;
                    case "-n":
                        content.append("\t").append(lineNumber++).append("\t").append(line).append("\n");
                        break;
                    case "-s":
                        if (line.trim().isEmpty())
                        {
                            if (!flagAlreadyHaveEmptyLine)
                            {
                                content.append("\n");
                                flagAlreadyHaveEmptyLine = true;
                            }
                        }
                        else
                        {
                            content.append(line).append(System.lineSeparator());
                            flagAlreadyHaveEmptyLine = false;
                        }
                        break;
                    case "-T":
                        content.append(line.replace("\t", "^I")).append("\n");
                        break;
                    case "-h":

                        break;
                    case "-v":

                        break;
                }
            }
        }
        catch (IOException e)
        {
            System.err.println("Ошибка при чтении файла " + file.getName() + ": " + e.getMessage());
        }
        System.out.println(content);
    }
}
