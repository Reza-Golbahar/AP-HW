package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuCommands implements Command {
    AddProduct("\\s*add\\s+product\\s+-n\\s+\"\\s*(?<name>.+)\\s*\"" +
            "\\s+-pc\\s+(?<producerCost>.+)\\s+-p\\s+(?<price>.+)\\s+-about\\s+" +
            "\"\\s*(?<aboutThisItem>\\S.+\\S)\\s*\"\\s+-np\\s+(?<numberOfProductsToSell>-?\\d+)\\s*"),
    ApplyDiscount("\\s*apply\\s+discount\\s+-p\\s+(?<productID>-?\\d+)\\s+-d\\s+" +
            "(?<discountPercentage>-?\\d+)\\s+-q\\s+(?<quantity>-?\\d+)\\s*"),
    ShowProfit("\\s*show\\s+profit\\s*"),
    ShowListOfProducts("\\s*show\\s+list\\s+of\\s+products\\s*"),
    AddStock("\\s*add\\s+stock\\s+-product\\s+(?<productID>-?\\d+)\\s+-amount\\s+" +
            "(?<amount>-?\\d+)\\s*"),
    UpdatePrice("\\s*update\\s+price\\s+-product\\s+(?<productID>-?\\d+)\\s+-price\\s+" +
            "(?<newPrice>.+)\\s*"),
    GoBack("\\s*go\\s+back\\s*");


    private final String pattern;

    StoreMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);

        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
