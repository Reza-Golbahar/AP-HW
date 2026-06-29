package models;

import models.enums.*;

import java.util.*;

public class Country {
    private CountryType type;
    private LeaderType currentLeader;
    private double stability;
    private boolean isPuppet;
    private ArrayList<Faction> factions;
    private Resources resources;
    private ArrayList<Country> puppets;

    public Country(CountryType type) {
        this.type = type;
        this.stability = 100;
        this.isPuppet = false;
        this.factions = new ArrayList<>();
        this.puppets = new ArrayList<>();
    }

    public void setInitialValues() {
        if (this.type == CountryType.Germany) {
            this.currentLeader = LeaderType.Hitler;
            this.resources = new Resources(60000000, 100000, 200000, 300000);
        } else if (this.type == CountryType.SovietUnion) {
            this.currentLeader = LeaderType.Stalin;
            this.resources = new Resources(160000000, 300000, 50000, 100000);
        } else if (this.type == CountryType.USA) {
            this.currentLeader = LeaderType.Roosevelt;
            this.resources = new Resources(120000000, 200000, 100000, 200000);
        } else if (this.type == CountryType.UK) {
            this.currentLeader = LeaderType.Churchill;
            this.resources = new Resources(30000000, 0, 1, 10);
        } else if (this.type == CountryType.Japan) {
            this.currentLeader = LeaderType.Hirohito;
            this.resources = new Resources(70000000, 50000, 50000, 50000);
        }
    }

    public void changeLeader(Ideology ideology) {
        if (stability < 50) {
            for (LeaderType leader : LeaderType.values()) {
                if (leader.getCountryType() == this.type && leader.getIdeology() == ideology) {
                    this.currentLeader = leader;
                    this.stability = 100; // Reset stability after election
                    System.out.println("Leader changed to: " + currentLeader.getName());
                    return;
                }
            }
            System.out.println("No leader available for this ideology!");
        } else {
            System.out.println("Election conditions not met!");
        }
    }

    public void decreaseStability(int amount) {
        stability = Math.max(0, stability - amount);
    }

    public String getName() {
        return type.getDisplayName();
    }

    public LeaderType getCurrentLeader() {
        return currentLeader;
    }

    public CountryType getType() {
        return type;
    }

    public void setType(CountryType type) {
        this.type = type;
    }

    public void setCurrentLeader(LeaderType currentLeader) {
        this.currentLeader = currentLeader;
    }

    public double getStability() {
        return stability;
    }

    public void setStability(double stability) {
        if (stability > 100)
            stability = 100;
        this.stability = stability;
    }

    public boolean isPuppet() {
        return isPuppet;
    }

    public void setPuppet(boolean puppet) {
        isPuppet = puppet;
    }

    public ArrayList<Faction> getFactions() {
        return factions;
    }

    public void setFactions(ArrayList<Faction> factions) {
        this.factions = factions;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

    public ArrayList<Country> getPuppets() {
        return puppets;
    }

    public void setPuppets(ArrayList<Country> puppets) {
        this.puppets = puppets;
    }

    public boolean isStable() {
        if (currentLeader.getIdeology().equals(Ideology.Communism)) {
            return stability >= 60;
        } else if (currentLeader.getIdeology().equals(Ideology.Democracy)) {
            return stability >= 50;
        }
        return stability >= 30;
    }
}

