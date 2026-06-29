package Decorators.PlayerDecorators;

import models.App;
import models.Player;

public class HeroPlayerCardDecorator extends BasePlayerCardDecorator {
    public HeroPlayerCardDecorator(Player player) {
        super(player);
        App.spendMoney(150_000_000);
    }

    @Override
    public int getShooting() {
        return Math.min(99, super.getShooting() + 4);
    }

    @Override
    public int getPace() {
        return Math.min(99, super.getPace() + 2);
    }

    @Override
    public int getDribbling() {
        return Math.min(99, super.getDribbling() + 2);
    }

    @Override
    public int getPhysic() {
        return Math.min(99, super.getPhysic() + 1);
    }

    @Override
    public int getPassing() {
        return Math.min(99, super.getPassing() + 1);
    }

    @Override
    public int getDefending() {
        return Math.min(99, super.getDefending() + 2);
    }

}
