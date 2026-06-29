import java.util.ArrayList;

public class Dog extends Mammal {
    public static ArrayList<String> humanUses = new ArrayList<>();

    static {
        humanUses.add("Guarding");
        humanUses.add("Police Dog");
        humanUses.add("Companionship");
        humanUses.add("Therapy Animal");
        humanUses.add("Service Animal");
    }
    public Dog(String name, String DNA) {
        super(name, DNA, "brown", 16.3, 12, true);
    }

    @Override
    public double survivalToMaturityRate(){
        return 0.9;
    }

}
