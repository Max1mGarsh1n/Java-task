import java.util.Scanner;

public class Menu {
    public void mainMenu() {
        boolean isNotExit = true;
        Scanner input = new Scanner(System.in);
        SupportMethods supportMethods = new SupportMethods();

        while (isNotExit) {
            String string = input.nextLine();

            if (supportMethods.IsCommand(string)) {
                switch (supportMethods.theBeginningOfTheLine(string)) {
                    case "$ cat":
                        CommandCat commandCat = new CommandCat(string);
                        if (commandCat.CommandIsGood())
                            commandCat.parseCommand();
                        else
                            System.out.println("Неправильно введена команда!");
                        break;
                    case "$ ls":

                        break;
                    case "exit":
                        isNotExit = false;
                        break;
                }
            }
        }
    }
}