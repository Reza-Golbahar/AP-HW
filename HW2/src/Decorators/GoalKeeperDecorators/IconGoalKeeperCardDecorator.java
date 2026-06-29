package Decorators.GoalKeeperDecorators;

import models.App;
import models.Goalkeeper;

public class IconGoalKeeperCardDecorator extends BaseGoalKeeperCardDecorator {
    public IconGoalKeeperCardDecorator(Goalkeeper goalkeeper) {
        super(goalkeeper);
        App.spendMoney(200_000_000);
    }

    @Override
    public int getDiving() {
        return Math.min(99, super.getDiving() + 5);
    }

    @Override
    public int getHandling() {
        return Math.min(99, super.getHandling() + 3);
    }

    @Override
    public int getReflex() {
        return Math.min(99, super.getReflex() + 2);
    }

    @Override
    public int getKicking() {
        return Math.min(99, super.getKicking() + 2);
    }

    @Override
    public int getSpeed() {
        return Math.min(99, super.getSpeed() + 5);
    }

}
