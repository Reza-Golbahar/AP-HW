package models;

import java.util.ArrayList;

public class Team {
    private static ArrayList<Athlete> athletes = new ArrayList<>();
    private static Player striker;
    private static Player defender;
    private static Goalkeeper goalkeeper;

    public static Player getStriker() {
        return striker;
    }

    public static void setStriker(Player striker) {
        Team.striker = striker;
    }

    public static Player getDefender() {
        return defender;
    }

    public static void setDefender(Player defender) {
        Team.defender = defender;
    }

    public static Goalkeeper getGoalkeeper() {
        return goalkeeper;
    }

    public static void setGoalkeeper(Goalkeeper goalkeeper) {
        Team.goalkeeper = goalkeeper;
    }

    public static ArrayList<Athlete> getAthletes() {
        return athletes;
    }

    public static void addAthlete(Athlete athlete) {
        athletes.add(athlete);
    }

    public static int getTeamPower() {
        int defenderPower;
        if (defender == null) defenderPower = 0;
        else defenderPower = defender.getPlayStyle().calculatePower(defender);

        int strikerPower;
        if (striker == null) strikerPower = 0;
        else strikerPower = striker.getPlayStyle().calculatePower(striker);

        int goaliePower;
        if (goalkeeper == null) goaliePower = 0;
        else goaliePower = (int)Math.floor((goalkeeper.getDiving() + goalkeeper.getHandling() +
                goalkeeper.getKicking() + goalkeeper.getPositioning() + goalkeeper.getReflex()
        + goalkeeper.getSpeed()) / 6.0);

        return (int)Math.floor((goaliePower + strikerPower + defenderPower) / 3.0);
    }
}
