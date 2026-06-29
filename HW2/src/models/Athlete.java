package models;

public abstract class Athlete {

    private final String name;
    private final String nationality;

    public Athlete(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public String getNationality() {
        return nationality;
    }
}
