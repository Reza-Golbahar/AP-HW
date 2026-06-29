
public abstract class Bird extends Animal {
    private boolean canFly;
    private int averageEggsPerYear;

    public Bird(String name, String DNA, String bodyColor, double averageWeight, int averageLifespan, boolean canFly, int averageEggsPerYear) {
        super(name, DNA, Habitat.LAND, bodyColor, true, averageWeight, averageLifespan);
        this.canFly = canFly;
        this.averageEggsPerYear = averageEggsPerYear;
    }



}
