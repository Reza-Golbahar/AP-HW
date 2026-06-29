package Decorators.GoalKeeperDecorators;

import models.Goalkeeper;

public class BaseGoalKeeperCardDecorator extends Goalkeeper {
    protected Goalkeeper wrappee;

    public BaseGoalKeeperCardDecorator(Goalkeeper goalkeeper) {
        super(goalkeeper.getName(), goalkeeper.getNationality(), goalkeeper.getDiving(),
                goalkeeper.getHandling(), goalkeeper.getReflex(), goalkeeper.getPositioning(),
                goalkeeper.getKicking(), goalkeeper.getSpeed()); //values are not important here, you could even pass wrong values

        this.wrappee = goalkeeper;
    }

    @Override
    public int getPrice(){
        return (int) Math.floor(
                (getDiving() + getHandling() + getReflex() +
                        getPositioning() + getKicking() + getSpeed()) / 6.0
        ) * 10_000_000;
    }

    @Override
    public int getDiving(){
        return wrappee.getDiving();
    }

    @Override
    public int getHandling(){
        return wrappee.getHandling();
    }

    @Override
    public int getReflex(){
        return wrappee.getReflex();
    }

    @Override
    public int getPositioning(){
        return wrappee.getPositioning();
    }

    @Override
    public int getKicking(){
        return wrappee.getKicking();
    }

    @Override
    public int getSpeed(){
        return wrappee.getSpeed();
    }
}
