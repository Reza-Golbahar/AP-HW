package Decorators.PlayerDecorators;

import models.App;
import models.Player;

public class IconPlayerCardDecorator extends BasePlayerCardDecorator {
    public IconPlayerCardDecorator(Player player) {
        super(player);
        App.spendMoney(200_000_000);
    }

    @Override
    public int getShooting() {
        return Math.min(99, super.getShooting() + 5);
    }

    @Override
    public int getPace() {
        return Math.min(99, super.getPace() + 3);
    }

    @Override
    public int getDribbling() {
        return Math.min(99, super.getDribbling() + 2);
    }

    @Override
    public int getPassing() {
        return Math.min(99, super.getPassing() + 2);
    }

    @Override
    public int getDefending() {
        return Math.min(99, super.getDefending() + 5);
    }


}
