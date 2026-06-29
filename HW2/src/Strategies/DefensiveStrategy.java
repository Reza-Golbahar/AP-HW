package Strategies;

import models.Player;

public class DefensiveStrategy implements PlayStyle {
    @Override
    public int calculatePower(Player player) {
        return (int)Math.floor((player.getDefending() + player.getPhysic() + player.getPassing()) / 3.0);
    }
}
