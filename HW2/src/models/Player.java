package models;

import Strategies.AggressiveStrategy;
import Strategies.BalancedStrategy;
import Strategies.DefensiveStrategy;
import Strategies.PlayStyle;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player extends Athlete{
    private int shooting;
    private int pace;
    private int dribbling;
    private int physic;
    private int passing;
    private int defending;
    private PlayStyle playStyle = new BalancedStrategy();

    public Player(String name, String nationality, int shooting, int pace,
                  int dribbling, int physic, int passing, int defending) {
        super(name, nationality);
        this.shooting = shooting;
        this.pace = pace;
        this.dribbling = dribbling;
        this.physic = physic;
        this.passing = passing;
        this.defending = defending;
    }

    public int getPrice() {
        return (int) Math.floor((shooting + pace + dribbling + physic + passing + defending) / 6.0) * 10_000_000;
    }

    public int getShooting() {
        return shooting;
    }

    public int getPace() {
        return pace;
    }

    public int getDribbling() {
        return dribbling;
    }

    public int getPhysic() {
        return physic;
    }

    public int getPassing() {
        return passing;
    }

    public int getDefending() {
        return defending;
    }

    public PlayStyle getPlayStyle() {
        return playStyle;
    }

    public void setPlayStyle(PlayStyle playStyle) {
        this.playStyle = playStyle;
    }

    @Override
    public String toString() {
        String style;
        if (playStyle instanceof AggressiveStrategy)
            style = "aggressive";
        else if (playStyle instanceof DefensiveStrategy)
            style = "defensive";
        else
            style = "balanced";

        return String.format("%s %s %d %d %d %d %d %d %s",
                getName(), getNationality(),
                getShooting(), getPace(), getDribbling(),
                getPhysic(), getPassing(), getDefending(), style);
    }
}
