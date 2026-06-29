import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trout extends BonyFish{

    public static ArrayList<String> humanUses = new ArrayList<>();

    static {
        humanUses.add("Food Source");
        humanUses.add("Fishing");
        humanUses.add("Aquaculture");
    }
    public Trout(String name, String DNA) {
        super(name, DNA, "olive-green", 1.6, 7, false);
    }


}
