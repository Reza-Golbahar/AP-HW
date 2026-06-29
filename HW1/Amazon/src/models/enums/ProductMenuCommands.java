package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProductMenuCommands implements Command {
    ShowProducts("\\s*show\\s+products\\s+-sortBy\\s+(?<sortCriteria>" +
            "(rate|higher price to lower|lower price to higher|number of sold))\\s*"),
    ShowNext10Products("\\s*show\\s+next\\s+10\\s+products\\s*"),
    ShowPrevious10Products("\\s*show\\s+past\\s+10\\s+products\\s*"),
    ShowInformation("\\s*show\\s+information\\s+of\\s+-id\\s+(?<productID>-?\\d+)\\s*"),
    RateProduct("\\s*Rate\\s+product\\s+-r\\s+(?<number>-?\\d+)\\s+(-m\\s+" +
            "\"\\s*(?<message>.+)\\s*\"\\s+)?-id\\s+(?<id>-?\\d+)\\s*"),
    AddToCart("\\s*add\\s+to\\s+cart\\s+-product\\s+(?<productID>-?\\d+)\\s+" +
            "-quantity\\s+(?<amount>-?\\d+)\\s*"),
    GoBack("\\s*go\\s+back\\s*");

    private final String pattern;

    ProductMenuCommands(String pattern) {
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
