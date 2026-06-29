package Decorators.PlayerDecorators;

import models.Player;

public class BasePlayerCardDecorator extends Player {
    protected Player wrappee;

    public BasePlayerCardDecorator(Player player) {
        super(player.getName(), player.getNationality(), player.getShooting(),
                player.getPace(), player.getDribbling(), player.getPhysic(), player.getPassing(),
                player.getDefending()); //values are not important here, you could even pass wrong values
        this.wrappee = player;
    }

    @Override
    public int getPrice() {
        return (int) Math.floor(
                (getShooting() + getPace() + getDribbling() +
                        getPhysic() + getPassing() + getDefending()) / 6.0
        ) * 10_000_000;
    }


    @Override
    public int getShooting() {
        return wrappee.getShooting();
    }

    @Override
    public int getPace() {
        return wrappee.getPace();
    }

    @Override
    public int getDribbling() {
        return wrappee.getDribbling();
    }

    @Override
    public int getPhysic() {
        return wrappee.getPhysic();
    }

    @Override
    public int getPassing() {
        return wrappee.getPassing();
    }

    @Override
    public int getDefending() {
        return wrappee.getDefending();
    }
}
