package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands implements Command {
    GoToLoginMenu("\\s*go\\s+to\\s+-m\\s+LoginMenu\\s*"),
    GoToUserMenu("\\s*go\\s+to\\s+-m\\s+UserMenu\\s*"),
    GoToProductMenu("\\s*go\\s+to\\s+-m\\s+ProductMenu\\s*"),
    GoToStoreMenu("\\s*go\\s+to\\s+-m\\s+StoreMenu\\s*"),
    Exit("\\s*exit\\s*");


    private final String pattern;

    MainMenuCommands(String pattern) {
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
