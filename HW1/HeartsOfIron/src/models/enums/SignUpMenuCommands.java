package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignUpMenuCommands implements Command {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    Exit("\\s*exit\\s*"),
    Register("\\s*register\\s+-username\\s+(?<username>\\S+)\\s+-password\\s+(?<password>.+)" +
            "\\s+-email\\s+(?<email>.+)\\s*"),
    UserName("[a-zA-Z][a-zA-Z\\d_]*"),
    Email("(?<mail>[a-zA-Z\\d]*\\.?[a-zA-Z\\d]*)@.+\\.com"),
    Login("\\s*login\\s*");

    private final String pattern;

    SignUpMenuCommands(String pattern) {
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
