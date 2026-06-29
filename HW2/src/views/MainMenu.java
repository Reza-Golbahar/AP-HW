package views;

import controllers.MainMenuController;
import models.enums.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu implements AppMenu{
    private final MainMenuController controller = new MainMenuController();

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = MainMenuCommands.BuildPlayer.getMatcher(input)) != null) {
            controller.buildPlayer(
                    matcher.group("name"),
                    matcher.group("nationality"),
                    Integer.parseInt(matcher.group("shooting")),
                    Integer.parseInt(matcher.group("pace")),
                    Integer.parseInt(matcher.group("dribbling")),
                    Integer.parseInt(matcher.group("physic")),
                    Integer.parseInt(matcher.group("passing")),
                    Integer.parseInt(matcher.group("defending"))
            );
        } else if ((matcher = MainMenuCommands.BuildGoalie.getMatcher(input)) != null) {
            controller.buildGoalie(
                    matcher.group("name"),
                    matcher.group("nationality"),
                    Integer.parseInt(matcher.group("diving")),
                    Integer.parseInt(matcher.group("handling")),
                    Integer.parseInt(matcher.group("reflex")),
                    Integer.parseInt(matcher.group("positioning")),
                    Integer.parseInt(matcher.group("kicking")),
                    Integer.parseInt(matcher.group("speed"))
            );
        } else if ((matcher = MainMenuCommands.BuyPlayer.getMatcher(input)) != null) {
            System.out.print(controller.buyPlayer(matcher.group("playerName")));
        } else if ((matcher = MainMenuCommands.SellPlayer.getMatcher(input)) != null) {
            controller.sellPlayer(matcher.group("playerName"));
        } else if ((matcher = MainMenuCommands.SelectRole.getMatcher(input)) != null) {
            controller.select(
                    matcher.group("role"),
                    matcher.group("playerName")
            );
        } else if ((matcher = MainMenuCommands.SetDecoration.getMatcher(input)) != null) {
            controller.setDecoration(
                    matcher.group("playerPosition"),
                    matcher.group("decoration")
            );
        } else if ((matcher = MainMenuCommands.SetPlayStyle.getMatcher(input)) != null) {
            controller.setPlayStyle(
                    matcher.group("playerPosition"),
                    matcher.group("style")
            );
        } else if ((matcher = MainMenuCommands.ShowLineUp.getMatcher(input)) != null) {
            System.out.println(controller.showLineUp());
        } else if ((matcher = MainMenuCommands.ShowMoney.getMatcher(input)) != null) {
            System.out.println(controller.showMoney());
        } else if ((matcher = MainMenuCommands.CalculateTeamPower.getMatcher(input)) != null) {
            System.out.println(controller.calculateTeamPower());
        } else if ((matcher = MainMenuCommands.Soot.getMatcher(input)) != null) {
            System.out.println(controller.soot());
        }
    }
}
