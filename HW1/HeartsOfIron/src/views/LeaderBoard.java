package views;

import controllers.LeaderBoardController;
import models.enums.LeaderBoardCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LeaderBoard implements AppMenu {
    private final LeaderBoardController controller = new LeaderBoardController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = LeaderBoardCommands.ShowCurrentMenu.getMatcher(input)) != null) {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = LeaderBoardCommands.Back.getMatcher(input)) != null) {
            controller.back();
        } else if ((matcher = LeaderBoardCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else if ((matcher = LeaderBoardCommands.ShowRanking.getMatcher(input)) != null) {
            System.out.println(controller.showRanking());
        } else if ((matcher = LeaderBoardCommands.ShowHistory.getMatcher(input)) != null) {
            System.out.println(controller.showHistory());
        } else {
            System.out.println("invalid command");
        }
    }
}
