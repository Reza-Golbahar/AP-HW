
public abstract class Animal {
    private String name;
    private String DNA;
    private Habitat habitat;
    private String bodyColor;
    private boolean isWarmBlooded;
    private double averageWeight;
    private int averageLifespan;
    public Animal(String name, String DNA, Habitat habitat, String bodyColor, boolean isWarmBlooded, double averageWeight, int averageLifespan) {
        this.name = name;
        this.DNA = DNA;
        this.habitat = habitat;
        this.bodyColor = bodyColor;
        this.isWarmBlooded = isWarmBlooded;
        this.averageWeight = averageWeight;
        this.averageLifespan = averageLifespan;
    }

    public double survivalToMaturityRate(){
        return 0.5;
    }

    public int yearlyReproductionYield(){
        return 100;
    }

    public String getName() {
        return name;
    }

    public String getDNA() {
        return DNA;
    }
}

