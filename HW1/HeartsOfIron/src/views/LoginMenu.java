package views;

import controllers.LoginMenuController;
import models.enums.LoginMenuCommands;
import models.enums.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = LoginMenuCommands.ShowCurrentMenu.getMatcher(input)) != null) {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = LoginMenuCommands.Back.getMatcher(input)) != null) {
            controller.back();
        } else if ((matcher = LoginMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else if ((matcher = LoginMenuCommands.Login.getMatcher(input)) != null) {
            System.out.println(controller.login(
                    matcher.group("username").trim(),
                    matcher.group("password").trim()
            ));
        } else if ((matcher = LoginMenuCommands.ForgetPassword.getMatcher(input)) != null) {
            System.out.println(controller.forgetPassword(
                    matcher.group("username").trim(),
                    matcher.group("email").trim()
            ));
        } else {
            System.out.println("invalid command");
        }
    }
}
