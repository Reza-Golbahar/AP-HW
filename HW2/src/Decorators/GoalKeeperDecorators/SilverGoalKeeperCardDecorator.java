package Decorators.GoalKeeperDecorators;

import models.App;
import models.Goalkeeper;

public class SilverGoalKeeperCardDecorator extends BaseGoalKeeperCardDecorator {
    public SilverGoalKeeperCardDecorator(Goalkeeper goalkeeper) {
        super(goalkeeper);
        App.spendMoney(70_000_000);
    }

    @Override
    public int getDiving() {
        return Math.min(99, super.getDiving() + 2);
    }

    @Override
    public int getHandling() {
        return Math.min(99, super.getHandling() + 2);
    }

    @Override
    public int getReflex() {
        return Math.min(99, super.getReflex() + 4);
    }

    @Override
    public int getPositioning(){
        return Math.min(99, super.getPositioning() + 2);
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
