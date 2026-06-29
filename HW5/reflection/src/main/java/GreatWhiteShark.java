
public class GreatWhiteShark extends CartilaginousFish {

    public GreatWhiteShark(String name, String DNA) {
        super(name, DNA, "white", 2250, 85);
    }

    @Override
    public int yearlyReproductionYield(){
        return 6;
    }

}
