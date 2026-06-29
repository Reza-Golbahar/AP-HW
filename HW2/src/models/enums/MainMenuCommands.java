package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands implements Command{
    BuildPlayer("\\s*build\\s+player\\s+(?<name>\\S+)\\s+(?<nationality>\\S+)\\s+(?<shooting>\\d+)\\s+" +
            "(?<pace>\\d+)\\s+(?<dribbling>\\d+)\\s+(?<physic>\\d+)\\s+(?<passing>\\d+)\\s+(?<defending>\\d+)\\s*"),
    BuildGoalie("\\s*build\\s+goalie\\s+(?<name>\\S+)\\s+(?<nationality>\\S+)\\s+(?<diving>\\d+)\\s+" +
            "(?<handling>\\d+)\\s+(?<reflex>\\d+)\\s+(?<positioning>\\d+)\\s+(?<kicking>\\d+)\\s+(?<speed>\\d+)\\s*"),
    BuyPlayer("\\s*buy\\s+(?<playerName>\\S+)\\s*"),
    SellPlayer("\\s*sell\\s+(?<playerName>\\S+)\\s*"),
    SelectRole("\\s*select\\s+(?<role>(st|cb|gk))\\s+(?<playerName>\\S+)\\s*"),
    SetDecoration("\\s*set\\s+decoration\\s+(?<playerPosition>st|cb|gk)\\s+(?<decoration>\\S+)\\s*"),
    SetPlayStyle("\\s*set\\s+play\\s+style\\s+(?<playerPosition>st|cb)\\s+(?<style>\\S+)\\s*"),
    ShowLineUp("\\s*show\\s+lineup\\s*"),
    ShowMoney("\\s*show\\s+money\\s*"),
    CalculateTeamPower("\\s*calculate\\s+team\\s+power\\s*"),
    Soot("\\s*soot\\s*");

    private final String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if (matcher.matches()) {
            return matcher;
        }
        return null;
    }
}
