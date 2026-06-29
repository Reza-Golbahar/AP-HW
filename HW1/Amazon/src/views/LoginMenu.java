package views;

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

        if ((matcher = LoginMenuCommands.CreateUserAccount.getMatcher(input)) != null) {
            System.out.println(controller.createUserAccount(
                    matcher.group("firstName"),
                    matcher.group("lastName"),
                    matcher.group("password"),
                    matcher.group("reEnteredPassword"),
                    matcher.group("emailAddress")
            ));
        } else if ((matcher = LoginMenuCommands.CreateMarketAccount.getMatcher(input)) != null) {
            System.out.println(controller.createMarketAccount(
                    matcher.group("brand"),
                    matcher.group("password"),
                    matcher.group("reEnteredPassword"),
                    matcher.group("emailAddress")
            ));
        } else if ((matcher = LoginMenuCommands.LoginAsUser.getMatcher(input)) != null) {
            System.out.println(controller.loginAsUser(
                    matcher.group("emailAddress"),
                    matcher.group("password")
            ));
        } else if ((matcher = LoginMenuCommands.LoginAsMarket.getMatcher(input)) != null) {
            System.out.println(controller.loginAsMarket(
                    matcher.group("emailAddress"),
                    matcher.group("password")
            ));
        } else if ((matcher = LoginMenuCommands.Logout.getMatcher(input)) != null) {
            System.out.println(controller.logout());
        } else if ((matcher = LoginMenuCommands.GoBack.getMatcher(input)) != null) {
            System.out.println(controller.goBack());
        } else if ((matcher = LoginMenuCommands.DeleteAccount.getMatcher(input)) != null) {
            System.out.println(controller.deleteAccount(
                    matcher.group("password"),
                    matcher.group("reEnteredPassword")
            ));
        } else {
            System.out.println("invalid command");
        }
    }

}
