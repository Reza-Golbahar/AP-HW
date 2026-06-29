package views;

import controllers.SignUpMenuController;
import models.enums.SignUpMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class SignUpMenu implements AppMenu {
    private final SignUpMenuController controller = new SignUpMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = SignUpMenuCommands.ShowCurrentMenu.getMatcher(input)) != null) {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = SignUpMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else if ((matcher = SignUpMenuCommands.Register.getMatcher(input)) != null) {
            System.out.println(controller.register(
                    matcher.group("username").trim(),
                    matcher.group("password").trim(),
                    matcher.group("email").trim()
            ));
        } else if ((matcher = SignUpMenuCommands.Login.getMatcher(input)) != null) {
            controller.login();
        } else {
            System.out.println("invalid command");
        }
    }
}
