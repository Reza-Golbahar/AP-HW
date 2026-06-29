package views;

import controllers.MainMenuController;
import models.enums.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu {
    private final MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = MainMenuCommands.GoToLoginMenu.getMatcher(input)) != null) {
            System.out.println(controller.goToLoginMenu());
        } else if ((matcher = MainMenuCommands.GoToProductMenu.getMatcher(input)) != null) {
            System.out.println(controller.goToProductMenu());
        } else if ((matcher = MainMenuCommands.GoToStoreMenu.getMatcher(input)) != null) {
            System.out.println(controller.goToStoreMenu());
        } else if ((matcher = MainMenuCommands.GoToUserMenu.getMatcher(input)) != null) {
            System.out.println(controller.goToUserMenu());
        } else if ((matcher = MainMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command");
        }
    }
}
