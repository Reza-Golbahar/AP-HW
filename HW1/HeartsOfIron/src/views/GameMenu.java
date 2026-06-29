package views;

import controllers.GameMenuController;
import models.Result;
import models.enums.GameMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();
    private boolean areCountriesChosen = false;

    @Override
    public void check(Scanner scanner) {
        if (!areCountriesChosen) {
            controller.chooseCountries(scanner);
        }
        areCountriesChosen = true;

        String input = scanner.nextLine();
        Matcher matcher;

        if ((matcher = GameMenuCommands.ShowCurrentMenu.getMatcher(input)) != null) {
            System.out.println(controller.showCurrentMenu());
        } else if ((matcher = GameMenuCommands.Exit.getMatcher(input)) != null) {
            controller.exit();
        } else if ((matcher = GameMenuCommands.SwitchPlayer.getMatcher(input)) != null) {
            System.out.println(controller.switchPlayer(matcher.group("username")));
        } else if ((matcher = GameMenuCommands.ShowDetail.getMatcher(input)) != null) {
            System.out.println(controller.showDetail(matcher.group("countryName")));
        } else if ((matcher = GameMenuCommands.TileOwner.getMatcher(input)) != null) {
            System.out.println(controller.tileOwner(matcher.group("index")));
        } else if ((matcher = GameMenuCommands.TileNeighbors.getMatcher(input)) != null) {
            System.out.println(controller.tileNeighbors(matcher.group("index")));
        } else if ((matcher = GameMenuCommands.TileSeaNeighbors.getMatcher(input)) != null) {
            System.out.println(controller.tileSeaNeighbors(matcher.group("index")));
        } else if ((matcher = GameMenuCommands.ShowWeather.getMatcher(input)) != null) {
            System.out.println(controller.showWeather(matcher.group("index")));
        } else if ((matcher = GameMenuCommands.ShowTerrain.getMatcher(input)) != null) {
            System.out.println(controller.showTerrain(matcher.group("index")));
        } else if ((matcher = GameMenuCommands.ShowBattalions.getMatcher(input)) != null) {
            System.out.println(controller.showBattalions(matcher.group("index")));
        } else if ((matcher = GameMenuCommands.ShowFactories.getMatcher(input)) != null) {
            System.out.println(controller.showFactories(matcher.group("index")));
        } else if ((matcher = GameMenuCommands.SetTerrain.getMatcher(input)) != null) {
            System.out.println(controller.setTerrain(
                    matcher.group("index"),
                    matcher.group("name")));
        } else if ((matcher = GameMenuCommands.SetWeather.getMatcher(input)) != null) {
            System.out.println(controller.setWeather(
                    matcher.group("index"),
                    matcher.group("name")));
        } else if ((matcher = GameMenuCommands.AddBattalion.getMatcher(input)) != null) {
            System.out.println(controller.addBattalion(
                    matcher.group("index"),
                    matcher.group("type"),
                    matcher.group("name")));
        } else if ((matcher = GameMenuCommands.MoveBattalion.getMatcher(input)) != null) {
            System.out.println(controller.moveBattalion(
                    matcher.group("index"),
                    matcher.group("name"),
                    matcher.group("destIndex")));
        } else if ((matcher = GameMenuCommands.UpgradeBattalion.getMatcher(input)) != null) {
            System.out.println(controller.upgradeBattalion(
                    matcher.group("index"),
                    matcher.group("name")));
        } else if ((matcher = GameMenuCommands.CreateFaction.getMatcher(input)) != null) {
            System.out.println(controller.createFaction(matcher.group("name")));
        } else if ((matcher = GameMenuCommands.JoinFaction.getMatcher(input)) != null) {
            System.out.println(controller.joinFaction(matcher.group("name")));
        } else if ((matcher = GameMenuCommands.LeaveFaction.getMatcher(input)) != null) {
            System.out.println(controller.leaveFaction(matcher.group("name")));
        } else if ((matcher = GameMenuCommands.BuildFactory.getMatcher(input)) != null) {
            System.out.println(controller.buildFactory(
                    matcher.group("index"),
                    matcher.group("factoryType"),
                    matcher.group("name")));
        } else if ((matcher = GameMenuCommands.RunFactory.getMatcher(input)) != null) {
            System.out.println(controller.runFactory(
                    matcher.group("index"),
                    matcher.group("name"),
                    matcher.group("manPowerCount")));
        } else if ((matcher = GameMenuCommands.Attack.getMatcher(input)) != null) {
            System.out.println(controller.attack(
                    matcher.group("myIndex"),
                    matcher.group("enemyIndex"),
                    matcher.group("battalionType")));
        } else if ((matcher = GameMenuCommands.StartCivilWar.getMatcher(input)) != null) {
            System.out.println(controller.startCivilWar(
                    matcher.group("tile1"),
                    matcher.group("tile2"),
                    matcher.group("battalionType")));
        } else if ((matcher = GameMenuCommands.Puppet.getMatcher(input)) != null) {
            System.out.println(controller.puppet(matcher.group("countryName")));
        } else if ((matcher = GameMenuCommands.StartElection.getMatcher(input)) != null) {
            controller.startElection(scanner);
        } else if ((matcher = GameMenuCommands.FinishGame.getMatcher(input)) != null) {
            areCountriesChosen = false;
            controller.finishGame();
        } else {
            System.out.println("invalid command");
        }
    }
}
