import java.util.List;

// Mammals
public abstract class Mammal extends Animal {
    private boolean canLiveWithHumans;

    public Mammal(String name, String DNA, String bodyColor, double averageWeight, int averageLifespan, boolean canLiveWithHumans) {
        super(name, DNA, Habitat.LAND, bodyColor, true, averageWeight, averageLifespan);
        this.canLiveWithHumans = canLiveWithHumans;
    }

    @Override
    public double survivalToMaturityRate(){
        return 0.6;
    }

    @Override
    public int yearlyReproductionYield(){
        return 6;
    }

}
