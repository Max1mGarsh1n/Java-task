import java.util.Scanner;

public class Menu
{
    public void mainMenu()
    {
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        SupportMethods supportMethods = new SupportMethods();
        while (flag)
        {
            String string = input.nextLine();

            if (supportMethods.IsCommand(string))
            {
                switch (supportMethods.theBeginningOfTheLine(string))
                {
                    case "$ cat":
                        CommandCat commandCat = new CommandCat();
                        commandCat.get_command(string);
                        commandCat.parseCommand();
                        break;
                    case "$ ls":

                        break;
                    case "exit":
                        flag = false;
                        break;
                }
            }
        }
    }
}