
public abstract class Fish extends Animal{
    private boolean isDangerous;
    public Fish(String name, String DNA,String bodyColor, double averageWeight, int averageLifespan, boolean isDangerous) {
        super(name, DNA, Habitat.WATER, bodyColor, false, averageWeight, averageLifespan);
        this.isDangerous = isDangerous;
    }

    @Override
    public double survivalToMaturityRate(){
        return 0.3;
    }


}
