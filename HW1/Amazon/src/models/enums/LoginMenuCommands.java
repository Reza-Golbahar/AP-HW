package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands implements Command {
    CreateUserAccount("\\s*create\\s+a\\s+user\\s+account\\s+-fn\\s+(?<firstName>.+)\\s+" +
            "-ln\\s+(?<lastName>.+)\\s+-p\\s+(?<password>.+)\\s+-rp\\s+(?<reEnteredPassword>.+)\\s+" +
            "-e\\s+(?<emailAddress>.+)\\s*"),
    Name("[A-Z][a-z]+"),
    Password("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]+$"),
    Email("([A-Za-z0-9]+(\\.[A-Za-z0-9]+)?|(\\.))@[a-z]+\\.com"),
    CreateMarketAccount("\\s*create\\s+a\\s+store\\s+account\\s+-b\\s+\"(?<brand>.+)\"\\s+" +
            "-p\\s+(?<password>.+)\\s+-rp\\s+(?<reEnteredPassword>.+)\\s+-e\\s+(?<emailAddress>.+)\\s*"),
    LoginAsUser("\\s*login\\s+as\\s+user\\s+-e\\s+(?<emailAddress>.+)\\s+-p\\s+(?<password>.+)\\s*"),
    LoginAsMarket("\\s*login\\s+as\\s+store\\s+-e\\s+(?<emailAddress>.+)\\s+-p\\s+(?<password>.+)\\s*"),
    Logout("\\s*logout\\s*"),
    DeleteAccount("\\s*delete\\s+account\\s+-p\\s+(?<password>.+)\\s+-rp\\s+(?<reEnteredPassword>.+)\\s*"),
    GoBack("\\s*go\\s+back\\s*");

    private final String pattern;

    LoginMenuCommands(String pattern) {
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
