import java.util.ArrayList;

public class Salmon extends BonyFish{

    public static ArrayList<String> humanUses = new ArrayList<>();

    static {
        humanUses.add("Food Source");
        humanUses.add("Fishing");
    }
    public Salmon(String name, String DNA) {
        super(name, DNA, "silver", 4.5, 5, false);
    }

    @Override
    public int yearlyReproductionYield(){
        return 5000;
    }

    Class c = Salmon.class.getSuperclass();
}
