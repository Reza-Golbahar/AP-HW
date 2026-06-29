package Builders;

import models.Athlete;
import models.Goalkeeper;

public class GoalkeeperBuilder implements AthleteBuilder {
    private String name;
    private String nationality;
    private int diving;
    private int handling;
    private int reflex;
    private int positioning;
    private int kicking;
    private int speed;

    @Override
    public void reset() {
        this.name = null;
        this.nationality = null;
        this.diving = 0;
        this.handling = 0;
        this.reflex = 0;
        this.positioning = 0;
        this.kicking = 0;
        this.speed = 0;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDiving(int diving) {
        this.diving = diving;
    }

    public void setHandling(int handling) {
        this.handling = handling;
    }

    public void setReflex(int reflex) {
        this.reflex = reflex;
    }

    public void setPositioning(int positioning) {
        this.positioning = positioning;
    }

    public void setKicking(int kicking) {
        this.kicking = kicking;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Athlete build() {
        return new Goalkeeper(name, nationality, diving, handling, reflex, positioning, kicking, speed);
    }
}
