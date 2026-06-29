package views;
/*
Explanation:
- This is a view class for the dashboard.
- This class should use to check inputs and print outputs for the dashboard.
- notice that : this class should not have any logic and just use it to get inputs and handle it to use correct methods in controller.
 */

import controllers.DashboardController;
import models.enums.DashboardCommands;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Dashboard implements AppMenu {
    private final DashboardController controller = new DashboardController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = DashboardCommands.CreateGroup.getMatcher(input)) != null) {
            System.out.println(controller.createGroup(
                    matcher.group("groupName").trim(),
                    matcher.group("groupType").trim()
            ));
        } else if ((matcher = DashboardCommands.ShowMyGroups.getMatcher(input)) != null) {
            System.out.println(controller.showMyGroups());
        } else if ((matcher = DashboardCommands.AddUser.getMatcher(input)) != null) {
            System.out.println(controller.addUser(
                    matcher.group("username").trim(),
                    matcher.group("email").trim(),
                    matcher.group("groupID").trim()
            ));
        } else if ((matcher = DashboardCommands.AddExpense.getMatcher(input)) != null) {
            System.out.println(controller.addExpense(scanner, matcher));
        }
        else if ((matcher = DashboardCommands.ShowBalance.getMatcher(input)) != null) {
            System.out.println(controller.showBalance(matcher.group("username").trim()));
        } else if ((matcher = DashboardCommands.SettleUp.getMatcher(input)) != null) {
            System.out.println(controller.settleUp(
                    matcher.group("username").trim(),
                    matcher.group("inputMoney").trim()
            ));
        } else if ((matcher = DashboardCommands.GoToProfileMenu.getMatcher(input)) != null) {
            System.out.println(controller.goToProfileMenu());
        } else if ((matcher = DashboardCommands.Logout.getMatcher(input)) != null) {
            System.out.println(controller.logout());
        } else if ((matcher = DashboardCommands.EXIT.getMatcher(input)) != null) {
            controller.exit();
        } else {
            System.out.println("invalid command!");
        }
    }
}
