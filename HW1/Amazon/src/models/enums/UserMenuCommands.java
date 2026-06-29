package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UserMenuCommands implements Command {
    ListMyOrders("\\s*list\\s+my\\s+orders\\s*"),
    ShowOrderDetails("\\s*show\\s+order\\s+details\\s+-id\\s+(?<orderID>-?\\d+)\\s*"),
    EditName("\\s*edit\\s+name\\s+-fn\\s+(?<firstName>.+)\\s+-ln\\s+(?<lastName>.+)\\s+-p\\s+" +
            "(?<password>.+)"),
    EditEmail("\\s*edit\\s+email\\s+-e\\s+(?<email>.+)\\s+-p\\s+(?<password>.+)\\s*"),
    EditPassword("\\s*edit\\s+password\\s+-np\\s+(?<newPassword>.+)\\s+-op\\s+(?<oldPassword>.+)\\s*"),
    ShowMyInfo("\\s*show\\s+my\\s+info\\s*"),
    AddAddress("\\s*add\\s+address\\s+-country\\s+(?<country>.+)\\s+-city\\s+(?<city>.+)\\s+" +
            "-street\\s+(?<street>.+)\\s+-postal\\s+(?<postal>.+)\\s*"),
    Postal("\\d{10}"),
    DeleteAddress("\\s*delete\\s+address\\s+-id\\s+(?<ID>-?\\d+)\\s*"),
    ListMyAddresses("\\s*list\\s+my\\s+addresses\\s*"),
    AddACreditCard("\\s*add\\s+a\\s+credit\\s+card\\s+-number\\s+(?<cardNumber>\\d+)\\s+-ed\\s+" +
            "(?<expirationDate>.+)\\s+-cvv\\s+(?<cvv>\\S+)\\s+-initialValue\\s+(?<initialValue>\\S+)\\s*"),
    CreditCardNumber("\\d{16}"),
    CreditCardExpirationDate("(?<month>\\d{2})\\s*/\\s*(?<year>\\d{2})"),
    CVV("\\d{3,4}"),
    ChargeCreditCard("\\s*Charge\\s+credit\\s+card\\s+-a\\s+(?<amount>\\S+)\\s+-id\\s+(?<id>-?\\d+)\\s*"),
    CheckCreditCardBalance("\\s*Check\\s+credit\\s+card\\s+balance\\s+-id\\s+(?<id>-?\\d+)\\s*"),
    ShowProductsInCart("\\s*show\\s+products\\s+in\\s+cart\\s*"),
    Checkout("\\s*checkout\\s+-card\\s+(?<cardID>-?\\d+)\\s+-address\\s+(?<addressID>-?\\d+)\\s*"),
    RemoveFromCart("\\s*remove\\s+from\\s+cart\\s+-product\\s+(?<productID>-?\\d+)\\s+-quantity\\s+(?<amount>-?\\d+)\\s*"),
    GoBack("\\s*go\\s+back\\s*");

    private final String pattern;

    UserMenuCommands(String pattern) {
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
