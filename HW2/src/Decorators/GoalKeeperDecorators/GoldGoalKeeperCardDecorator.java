package Decorators.GoalKeeperDecorators;

import models.App;
import models.Goalkeeper;

public class GoldGoalKeeperCardDecorator extends BaseGoalKeeperCardDecorator {
    public GoldGoalKeeperCardDecorator(Goalkeeper goalkeeper) {
        super(goalkeeper);
        App.spendMoney(100_000_000);
    }

    @Override
    public int getDiving() {
        return Math.min(99, super.getDiving() + 3);
    }

    @Override
    public int getReflex() {
        return Math.min(99, super.getReflex() + 3);
    }

    @Override
    public int getPositioning() {
        return Math.min(99, super.getPositioning() + 4);
    }

    @Override
    public int getKicking() {
        return Math.min(99, super.getKicking() + 3);
    }

    @Override
    public int getSpeed() {
        return Math.min(99, super.getSpeed() + 1);
    }

}
