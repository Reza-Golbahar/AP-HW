package Decorators.PlayerDecorators;

import models.App;
import models.Player;

public class BronzePlayerCardDecorator extends BasePlayerCardDecorator {
    public BronzePlayerCardDecorator(Player player){
        super(player);
        App.spendMoney(50_000_000);
    }

    @Override
    public int getPace() {
        return Math.min(99, super.getPace() + 1);
    }

    @Override
    public int getDribbling() {
        return Math.min(99, super.getDribbling() + 3);
    }

    @Override
    public int getDefending() {
        return Math.min(99, super.getDefending() + 2);
    }

}
