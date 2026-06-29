package Strategies;

import models.Player;

public class AggressiveStrategy implements PlayStyle {
    @Override
    public int calculatePower(Player player) {
        return (int)Math.floor((player.getPace() + player.getShooting() + player.getDribbling()) / 3.0);
    }
}
