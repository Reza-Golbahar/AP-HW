package Decorators.GoalKeeperDecorators;

import models.App;
import models.Goalkeeper;

public class HeroGoalKeeperCardDecorator extends BaseGoalKeeperCardDecorator {
    public HeroGoalKeeperCardDecorator(Goalkeeper goalkeeper) {
        super(goalkeeper);
        App.spendMoney(150_000_000);
    }

    @Override
    public int getDiving() {
        return Math.min(99, super.getDiving() + 4);
    }

    @Override
    public int getHandling() {
        return Math.min(99, super.getHandling() + 2);
    }

    @Override
    public int getReflex() {
        return Math.min(99, super.getReflex() + 2);
    }

    @Override
    public int getPositioning() {
        return Math.min(99, super.getPositioning() + 1);
    }

    @Override
    public int getKicking() {
        return Math.min(99, super.getKicking() + 1);
    }

    @Override
    public int getSpeed() {
        return Math.min(99, super.getSpeed() + 2);
    }


}
