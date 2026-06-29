package models.enums;

/*
Explanation:
- We need to define a currency enum.
- currencies in out app are some constants that we need to define them in our code once and use them in our code.
- each currency has some data, put them here and use some methods to work with currencies so simply.
 */

public enum Currency {
    // value of 1 measure of each currency
    GTC(10),
    SUD(5),
    QTR(2);

    private final int relationalValue;

    Currency(int relationalValue) {
        this.relationalValue = relationalValue;
    }

    public int getRelationalValue() {
        return relationalValue;
    }
}
