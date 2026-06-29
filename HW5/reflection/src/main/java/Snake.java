import java.util.ArrayList;

public class Snake extends Reptile {

    public static ArrayList<String> humanUses = new ArrayList<>();

    static {
        humanUses.add("Medicinal Research");
        humanUses.add("Skin Trade");
        humanUses.add("Pest Control");
    }

    public Snake(String name, String DNA) {
        super(name, DNA, "yellow", 3.4, 13, true, true);
    }

    @Override
    public double survivalToMaturityRate(){
        return 0.6;
    }

    @Override
    public int yearlyReproductionYield(){
        return 15;
    }
}
