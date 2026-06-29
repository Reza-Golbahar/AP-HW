package Decorators.PlayerDecorators;

import models.App;
import models.Player;

public class GoldPlayerCardDecorator extends BasePlayerCardDecorator{
    public GoldPlayerCardDecorator(Player player) {
        super(player);
        App.spendMoney(100_000_000);
    }

    @Override
    public int getShooting() {
        return Math.min(99, super.getShooting() + 3);
    }

    @Override
    public int getDribbling() {
        return Math.min(99, super.getDribbling() + 3);
    }

    @Override
    public int getPhysic() {
        return Math.min(99, super.getPhysic() + 4);
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
