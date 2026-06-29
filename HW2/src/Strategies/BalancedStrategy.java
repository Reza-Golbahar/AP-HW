package Strategies;

import models.Player;

public class BalancedStrategy implements PlayStyle {
    @Override
    public int calculatePower(Player player) {
        return (int)(Math.floor((player.getPace() + player.getShooting() + player.getDribbling() +
                player.getDefending() + player.getPhysic() + player.getPassing()) / 6.0));
    }
}
