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

        if ((matcher = MainMenuCommands.ShowCurrentMenu.getMatcher(input)) != null) {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = MainMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else if ((matcher = MainMenuCommands.Logout.getMatcher(input)) != null) {
            controller.logout();
        } else if ((matcher = MainMenuCommands.LeaderBoard.getMatcher(input)) != null) {
            controller.leaderboard();
        } else if ((matcher = MainMenuCommands.Play.getMatcher(input)) != null) {
            System.out.println(controller.play(
                    new String[]{matcher.group("player2"),
                            matcher.group("player3"),
                            matcher.group("player4"),
                            matcher.group("player5")}
            ));
        } else {
            System.out.println("invalid command");
        }
    }
}
