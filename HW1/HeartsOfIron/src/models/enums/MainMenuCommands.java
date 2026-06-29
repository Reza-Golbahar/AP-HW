package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands implements Command {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    Logout("\\s*logout\\s*"),
    Exit("\\s*exit\\s*"),
    LeaderBoard("\\s*leaderboard\\s*"),
    Play("\\s*play(\\s+(?<player2>\\S+))?(\\s+(?<player3>\\S+))?(\\s+(?<player4>\\S+))?(\\s+(?<player5>\\S+))?\\s*");


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
