package views;
/*
Explanation:
- This is a view class for the login menu.
- This class should use to check inputs and print outputs for the login menu.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */


import controllers.LoginMenuController;
import models.enums.LoginMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu implements AppMenu {
    private final LoginMenuController controller = new LoginMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = LoginMenuCommands.Login.getMatcher(input)) != null) {
            System.out.println(controller.login(
                    matcher.group("username"),
                    matcher.group("password")
            ));
        } else if ((matcher = LoginMenuCommands.ForgetPassword.getMatcher(input)) != null) {
            System.out.println(controller.forgetPassword(
                    matcher.group("username"),
                    matcher.group("email")
            ));
        } else if ((matcher = LoginMenuCommands.GoToSignUpMenu.getMatcher(input)) != null) {
            System.out.println(controller.goToSignUpMenu());
        } else if ((matcher = LoginMenuCommands.EXIT.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }
}
