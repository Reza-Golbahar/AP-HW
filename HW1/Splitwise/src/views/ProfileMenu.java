package views;
/*
Explanation: 
- This is a view class for profile menu.
- This class should use to check inputs and print outputs for profile menu.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */


import controllers.ProfileMenuController;
import models.enums.ProfileMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu implements AppMenu {
    private final ProfileMenuController controller = new ProfileMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;
        if ((matcher = ProfileMenuCommands.ShowUserInfo.getMatcher(input)) != null) {
            System.out.println(controller.showUserInfo());
        } else if ((matcher = ProfileMenuCommands.ChangeCurrency.getMatcher(input)) != null) {
            System.out.println(controller.changeCurrency(matcher.group("newCurrency")));
        } else if ((matcher = ProfileMenuCommands.ChangeUsername.getMatcher(input)) != null) {
            System.out.println(controller.changeUsername(matcher.group("newUsername")));
        } else if ((matcher = ProfileMenuCommands.ChangePassword.getMatcher(input)) != null) {
            System.out.println(controller.changePassword(
                    matcher.group("oldPassword"),
                    matcher.group("newPassword")));
        } else if ((matcher = ProfileMenuCommands.BackToDashBoard.getMatcher(input)) != null) {
            System.out.println(controller.backToDashBoard());
        } else if ((matcher = ProfileMenuCommands.EXIT.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }

}
