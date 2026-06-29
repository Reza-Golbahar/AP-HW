package views;

/*
Explanation:
- This is a view class for the SignUpMenu.
- This class should use to check inputs and print outputs for the SignUpMenu.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */


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
        if ((matcher = SignUpMenuCommands.Register.getMatcher(input)) != null) {
            System.out.println(controller.register(
                    matcher.group("username").trim(),
                    matcher.group("password").trim(),
                    matcher.group("email").trim(),
                    matcher.group("name").trim()));
        } else if ((matcher = SignUpMenuCommands.GoToLoginMenu.getMatcher(input)) != null) {
            System.out.println(controller.goToLoginMenu());
        } else if ((matcher = SignUpMenuCommands.EXIT.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }
}

