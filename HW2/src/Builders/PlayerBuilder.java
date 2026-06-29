package Builders;

import models.Athlete;
import models.Player;

public class PlayerBuilder implements AthleteBuilder {
    private String name;
    private String nationality;
    private int shooting;
    private int pace;
    private int dribbling;
    private int physic;
    private int passing;
    private int defending;


    @Override
    public void reset() {
        this.name = null;
        this.nationality = null;
        this.shooting = 0;
        this.pace = 0;
        this.dribbling = 0;
        this.physic = 0;
        this.passing = 0;
        this.defending = 0;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public void setPace(int pace) {
        this.pace = pace;
    }

    public void setDribbling(int dribbling) {
        this.dribbling = dribbling;
    }

    public void setPhysic(int physic) {
        this.physic = physic;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public void setDefending(int defending) {
        this.defending = defending;
    }

    @Override
    public Athlete build(){
        return new Player(name, nationality, shooting, pace, dribbling, physic, passing, defending);
    }
}
