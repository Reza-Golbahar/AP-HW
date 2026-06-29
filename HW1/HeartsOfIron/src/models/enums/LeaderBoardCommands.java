package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LeaderBoardCommands implements Command {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    Back("\\s*back\\s*"),
    Exit("\\s*exit\\s*"),
    ShowRanking("\\s*show\\s+ranking\\s*"),
    ShowHistory("\\s*show\\s+history\\s*");

    private final String pattern;

    LeaderBoardCommands(String pattern) {
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
