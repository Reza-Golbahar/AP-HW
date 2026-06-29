package models;

import models.enums.Ideology;

import java.util.*;

public class GameHistoryEntry {
    private final List<Player> players;

    public GameHistoryEntry(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    @Override
    public String toString() {
        StringBuilder historyEntry = new StringBuilder();
        for (Player player : players) {
            double points = player.getPointsInThisGame();

            // Double the points if the player's leader is fascist
            if (player.getPlayerCountry().getCurrentLeader().getIdeology() == Ideology.Fascism) {
                points *= 2;
            }

            // Append the player's details with possibly doubled points
            historyEntry.append("%s %s %.0f\n".formatted(
                    player.getUser().getUsername(),
                    player.getPlayerCountry().getName(),
                    points
            ));
        }
        return historyEntry.toString().trim();
    }
}
