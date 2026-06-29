
public abstract class Amphibian extends Animal {
    private boolean isDangerous;
    public Amphibian(String name, String DNA, String bodyColor, double averageWeight, int averageLifespan, boolean isDangerous) {
        super(name, DNA, Habitat.WATER_AND_LAND , bodyColor, false, averageWeight, averageLifespan);
        this.isDangerous = isDangerous;
    }

}
