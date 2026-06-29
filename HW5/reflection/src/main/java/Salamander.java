
public abstract class Salamander extends Amphibian {

    public Salamander(String name, String DNA, String bodyColor, double averageWeight, int averageLifespan, boolean isDangerous) {
        super(name, DNA, bodyColor, averageWeight, averageLifespan, isDangerous);
    }

    @Override
    public double survivalToMaturityRate(){
        return 0.3;
    }

}
