import java.util.ArrayList;

public class EasternNewt extends Salamander{

    public static ArrayList<String> humanUses = new ArrayList<>();

    static {
        humanUses.add("Environmental Indicators");
        humanUses.add("Research Toxicology Studies");
        humanUses.add("Pet Trade");
    }
    public EasternNewt(String name, String DNA) {
        super(name, DNA, "red-orange", 0.0035, 14,true);
    }

    @Override
    public int yearlyReproductionYield(){
        return 100;
    }
}
