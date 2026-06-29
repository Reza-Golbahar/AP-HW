package Decorators.GoalKeeperDecorators;

import models.App;
import models.Goalkeeper;

public class BronzeGoalKeeperCardDecorator extends BaseGoalKeeperCardDecorator {
    public BronzeGoalKeeperCardDecorator(Goalkeeper goalkeeper) {
        super(goalkeeper);
        App.spendMoney(50_000_000);
    }

    @Override
    public int getHandling() {
        return Math.min(99, super.getHandling() + 1);
    }

    @Override
    public int getReflex() {
        return Math.min(99, super.getReflex() + 3);
    }

    @Override
    public int getSpeed() {
        return Math.min(99, super.getSpeed() + 2);
    }
}
