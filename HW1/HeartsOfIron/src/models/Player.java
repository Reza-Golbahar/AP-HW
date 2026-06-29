package models;

import models.enums.BattalionType;
import models.enums.Ideology;

public class Player {
    private User user;
    private final boolean isGuest;
    Country playerCountry;
    private double pointsInThisGame;

    public Player(User user, boolean isGuest) {
        this.user = user;
        this.isGuest = isGuest;
    }

    public double getPointsInThisGame() {
        return pointsInThisGame;
    }

    public Country getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(Country playerCountry) {
        this.playerCountry = playerCountry;
        playerCountry.setInitialValues();
    }

    public User getUser() {
        return user;
    }

    public void setUsername(User user) {
        this.user = user;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public void getWinningPoints(BattalionType type, double strengthDifference) {
        double newPoints = 0;
        if (type.equals(BattalionType.Infantry))
            newPoints = (strengthDifference * 5);
        else if (type.equals(BattalionType.Panzer))
            newPoints = (strengthDifference * 7);
        else if (type.equals(BattalionType.Navy))
            newPoints = (strengthDifference * 10);
        else if (type.equals(BattalionType.AirForce))
            newPoints = (strengthDifference * 15);

        pointsInThisGame += (newPoints * 10);
    }

    public void getLosingPoints(BattalionType type, double strengthDifference) {
        double newPoints = 0;
        if (type.equals(BattalionType.Infantry))
            newPoints = (strengthDifference * 5);
        else if (type.equals(BattalionType.Panzer))
            newPoints = (strengthDifference * 7);
        else if (type.equals(BattalionType.Navy))
            newPoints = (strengthDifference * 10);
        else if (type.equals(BattalionType.AirForce))
            newPoints = (strengthDifference * 15);

        pointsInThisGame -= (newPoints * 5);
    }


    public void addPointsToUser() {
        if (playerCountry.getCurrentLeader().getIdeology().equals(Ideology.Fascism))
            user.setPoints(user.getPoints() + (pointsInThisGame * 2.0));
        else user.setPoints(user.getPoints() + pointsInThisGame);
    }
}
