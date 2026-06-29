package controllers;

import Decorators.GoalKeeperDecorators.*;
import Decorators.PlayerDecorators.*;
import Directors.AthleteDirector;
import Builders.GoalkeeperBuilder;
import Builders.PlayerBuilder;
import Strategies.AggressiveStrategy;
import Strategies.BalancedStrategy;
import Strategies.DefensiveStrategy;
import Strategies.PlayStyle;
import models.*;
import models.enums.Menu;

public class MainMenuController {
    public void buildPlayer(String name, String nationality, int shooting, int pace,
                            int dribbling, int physic, int passing, int defending) {
        PlayerBuilder playerBuilder = new PlayerBuilder();


        AthleteDirector director = new AthleteDirector(playerBuilder);
        Player player = (Player) director.makePlayer(name, nationality, shooting, pace, dribbling,
        physic, passing, defending);

        App.getAllAthletes().add(player);
        playerBuilder.reset();
    }


    public void buildGoalie(String name, String nationality, int diving, int handling,
                            int reflex, int positioning, int kicking, int speed) {
        GoalkeeperBuilder goalkeeperBuilder = new GoalkeeperBuilder();

        AthleteDirector director = new AthleteDirector(goalkeeperBuilder);
        Goalkeeper goalie = (Goalkeeper) director.makeGoalie(name, nationality, diving, handling,
        reflex, positioning, kicking, speed);

        App.getAllAthletes().add(goalie);
        goalkeeperBuilder.reset();
    }


    public Result buyPlayer(String playerName) {
        Athlete athlete = App.findAthlete(playerName);
        int cost = athlete.getPrice();
        if (cost > App.getMoneyLeft()) {
            return new Result(false, "8 - 2 \n");
        }
        App.spendMoney(cost);
        Team.addAthlete(athlete);
        return new Result(true, "");
    }


    public void sellPlayer(String playerName) {
        Athlete athlete = App.findAthlete(playerName);
        int cost = athlete.getPrice();
        Team.getAthletes().remove(athlete);
        App.addMoney(cost / 2);
    }


    public void select(String role, String playerName) {
        Athlete athlete = App.findAthlete(playerName);
        switch (role) {
            case "st" -> {
                Team.setStriker((Player) athlete);
            }
            case "cb" -> {
                Team.setDefender((Player) athlete);
            }
            case "gk" -> {
                Team.setGoalkeeper((Goalkeeper) athlete);
            }
        }
    }


    public void setDecoration(String position, String decoration) {
        if (position.equals("gk")) {
            Goalkeeper goalkeeper = Team.getGoalkeeper();
            if (goalkeeper == null) return;

            switch (decoration.toLowerCase()) {
                case "bronze" -> goalkeeper = new BronzeGoalKeeperCardDecorator(goalkeeper);
                case "silver" -> goalkeeper = new SilverGoalKeeperCardDecorator(goalkeeper);
                case "gold" -> goalkeeper = new GoldGoalKeeperCardDecorator(goalkeeper);
                case "hero" -> goalkeeper = new HeroGoalKeeperCardDecorator(goalkeeper);
                case "icon" -> goalkeeper = new IconGoalKeeperCardDecorator(goalkeeper);
            }

            Team.setGoalkeeper(goalkeeper);
        } else {
            Player player;
            if (position.equals("st")) {
                player = Team.getStriker();
            } else {
                player = Team.getDefender();
            }

            if (player == null) return;

            switch (decoration.toLowerCase()) {
                case "bronze" -> player = new BronzePlayerCardDecorator(player);
                case "silver" -> player = new SilverPlayerCardDecorator(player);
                case "gold" -> player = new GoldPlayerCardDecorator(player);
                case "hero" -> player = new HeroPlayerCardDecorator(player);
                case "icon" -> player = new IconPlayerCardDecorator(player);
            }

            if (position.equals("st")) {
                Team.setStriker(player);
            } else {
                Team.setDefender(player);
            }
        }
    }


    public void setPlayStyle(String position, String style){
        PlayStyle playStyle;
        if (style.equals("balanced")) {
            App.spendMoney(3_000_000);
            playStyle = new BalancedStrategy();
        } else if (style.equals("aggressive")) {
            App.spendMoney(5_000_000);
            playStyle = new AggressiveStrategy();
        } else if (style.equals("defensive")) {
            App.spendMoney(4_000_000);
            playStyle = new DefensiveStrategy();
        } else {
            return;
        }

        if (position.equals("cb")) {
            Team.getDefender().setPlayStyle(playStyle);
        } else if (position.equals("st")) {
            Team.getStriker().setPlayStyle(playStyle);
        }
    }


    public Result showLineUp() {
        StringBuilder result = new StringBuilder();

        Player striker = Team.getStriker();
        result.append("striker:");
        if (striker != null) {
            result.append(" %s\n".formatted(striker.toString()));
        } else {
            result.append("\n");
        }

        Player defender = Team.getDefender();
        result.append("center back:");
        if (defender != null) {
            result.append(" %s\n".formatted(defender.toString()));
        } else {
            result.append("\n");
        }

        Goalkeeper goalkeeper = Team.getGoalkeeper();
        result.append("goal keeper:");
        if (goalkeeper != null) {
            result.append(" %s".formatted(goalkeeper.toString()));
        }

        return new Result(true, result.toString());
    }


    public Result showMoney() {
        return new Result(true, "%d".formatted(App.getMoneyLeft()));
    }


    public Result calculateTeamPower() {
        return new Result(true, "%d".formatted(Team.getTeamPower()));
    }


    public Result soot() {
        String result;
        int teamPower = Team.getTeamPower();
        if (teamPower > 90) {
            result = "Visca Barca";
        } else if (teamPower == 90) {
            result = "draw";
        } else {
            result = "Hala Madrid";
        }

        App.setCurrentMenu(Menu.ExitMenu);
        return new Result(true, result);
    }
}
