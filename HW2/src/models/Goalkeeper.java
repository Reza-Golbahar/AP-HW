package models;

import java.util.ArrayList;

public class Goalkeeper extends Athlete {
    private int diving;
    private int handling;
    private int reflex;
    private int positioning;
    private int kicking;
    private int speed;

    public Goalkeeper(String name, String nationality, int diving, int handling, int reflex,
                      int positioning, int kicking, int speed) {
        super(name, nationality);
        this.diving = diving;
        this.handling = handling;
        this.reflex = reflex;
        this.positioning = positioning;
        this.kicking = kicking;
        this.speed = speed;
    }

    public int getPrice() {
        return (int) Math.floor((diving + handling + reflex + positioning
                + kicking + speed) / 6.0) * 10000000;
    }

    public int getDiving() {
        return diving;
    }

    public int getHandling() {
        return handling;
    }

    public int getReflex() {
        return reflex;
    }

    public int getPositioning() {
        return positioning;
    }

    public int getKicking() {
        return kicking;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d %d %d %d %d %d",
                getName(), getNationality(),
                getDiving(), getHandling(), getReflex(),
                getPositioning(), getKicking(), getSpeed());
    }
}
