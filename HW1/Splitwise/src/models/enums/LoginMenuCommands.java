package models.enums;

/*
Explanation:
- we have commands in our login menu and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands implements Command {
    Login("\\s*login\\s+-u\\s+(?<username>.+)\\s+-p\\s+(?<password>.+)\\s*"),
    ForgetPassword("\\s*forget-password\\s+-u\\s+(?<username>.+)\\s+-e\\s+(?<email>.+)\\s*"),
    GoToSignUpMenu("\\s*go\\s+to\\s+signup\\s+menu\\s*"),
    EXIT("\\s*exit\\s*");

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