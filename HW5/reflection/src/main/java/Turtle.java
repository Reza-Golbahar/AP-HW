import java.util.ArrayList;

public class Turtle extends Reptile {

    public static ArrayList<String> humanUses = new ArrayList<>();

    static {
        humanUses.add("Pet Trade");
    }

    public Turtle(String name, String DNA) {
        super(name, DNA, "greenish", 1.9, 38, false, false);
    }

    @Override
    public double survivalToMaturityRate(){
        return 0.05;
    }

}
