
public abstract class BonyFish extends Fish {
    public BonyFish(String name, String DNA, String bodyColor, double averageWeight, int averageLifespan, boolean isDangerous) {
        super(name, DNA, bodyColor, averageWeight, averageLifespan, isDangerous);
    }

    @Override
    public int yearlyReproductionYield(){
        return 1500;
    }
}
