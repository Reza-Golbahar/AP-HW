public class Eagle extends Bird {
    private String wingColor;

    public void setWingColor(String wingColor) {
        this.wingColor = wingColor;
    }

    public Eagle(String name, String DNA) {
        super(name, DNA, "dark gray-brown", 4.5, 25, true, 2);
        this.wingColor = "dark brown";
    }

    @Override
    public int yearlyReproductionYield(){
        return 2;
    }

}
