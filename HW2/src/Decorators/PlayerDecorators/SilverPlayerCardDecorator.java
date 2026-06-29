package Decorators.PlayerDecorators;

import models.App;
import models.Player;

public class SilverPlayerCardDecorator extends BasePlayerCardDecorator{
    public SilverPlayerCardDecorator(Player player) {
        super(player);
        App.spendMoney(70_000_000);
    }

    @Override
    public int getShooting() {
        return Math.min(99, super.getShooting() + 2);
    }

    @Override
    public int getPace() {
        return Math.min(99, super.getPace() + 2);
    }

    @Override
    public int getDribbling() {
        return Math.min(99, super.getDribbling() + 4);
    }

    @Override
    public int getPhysic() {
        return Math.min(99, super.getPhysic() + 2);
    }

    @Override
    public int getPassing() {
        return Math.min(99, super.getPassing() + 3);
    }

    @Override
    public int getDefending() {
        return Math.min(99, super.getDefending() + 1);
    }
}
