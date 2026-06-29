package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands implements Command {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    Back("\\s*back\\s*"),
    Exit("\\s*exit\\s*"),
    Login("\\s*login\\s+-username\\s+(?<username>\\S+)\\s+-password\\s+(?<password>\\S+)\\s*"),
    ForgetPassword("\\s*forget-password\\s+-username\\s+(?<username>\\S+)\\s+" +
            "-email\\s+(?<email>\\S+)\\s*");

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
