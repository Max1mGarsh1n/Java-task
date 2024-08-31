import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class CommandCat
{
    private String command;
    private String option;
    private String userInput;
    private ArrayList<File> source_files;
    private File receiver_file;

    public void get_command(String string)
    {
        command = string;
    }
    public boolean isOption(String string)
    {
        String[] options = { "-b", "-E", "-n", "-s", "-T", "-h", "-v"};
        return Arrays.asList(options).contains(string);
    }
    public boolean isThereAnOption()
    {
        return option != null;
    }
    public void parseCommand()
    {
        String[] parts = command.split(" ");
        source_files = new ArrayList<>();

        if (parts.length > 2 && isOption(parts[2]))
            option = parts[2];

        boolean flag_user_input = false;
        boolean flag_receiver_file = false;
        for (int i = isThereAnOption() ?  3 : 2; i < parts.length; ++i)
        {
            if (Objects.equals(parts[i], "-"))
            {
                Scanner input = new Scanner(System.in);
                userInput = input.nextLine();
            }
            else if (Objects.equals(parts[i], ">"))
            {
                receiver_file = new File(parts[i] + ".txt");
            }
            else
                source_files.add(new File(parts[i] + ".txt"));
        }

    }
    /*public void withoutOption() // вывод файла в консоль построчно
    {
        for (File file : files)
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
    }*/
}
