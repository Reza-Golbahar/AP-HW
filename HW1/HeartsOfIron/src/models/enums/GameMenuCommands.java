package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    ShowCurrentMenu("\\s*show\\s+current\\s+menu\\s*"),
    Exit("\\s*exit\\s*"),

    SwitchPlayer("\\s*switch\\s+player\\s+(?<username>\\S+)\\s*"),
    ShowDetail("\\s*show\\s+detail\\s+(?<countryName>.+)\\s*"),

    TileOwner("\\s*tile\\s+owner\\s+(?<index>-?\\d+)\\s*"),
    TileNeighbors("\\s*tile\\s+neighbors\\s+(?<index>-?\\d+)\\s*"),
    TileSeaNeighbors("\\s*tile\\s+sea\\s+neighbors\\s+(?<index>-?\\d+)\\s*"),

    ShowWeather("\\s*show\\s+weather\\s+(?<index>-?\\d+)\\s*"),
    ShowTerrain("\\s*show\\s+terrain\\s+(?<index>-?\\d+)\\s*"),
    ShowBattalions("\\s*show\\s+battalions\\s+(?<index>-?\\d+)\\s*"),
    ShowFactories("\\s*show\\s+factories\\s+(?<index>-?\\d+)\\s*"),

    SetTerrain("\\s*set\\s+terrain\\s+(?<index>-?\\d+)\\s+(?<name>\\S+)\\s*"),
    SetWeather("\\s*set\\s+weather\\s+(?<index>-?\\d+)\\s+(?<name>\\S+)\\s*"),

    AddBattalion("\\s*add\\s+battalion\\s+(?<index>-?\\d+)\\s+(?<type>\\S+)\\s+(?<name>\\S+)\\s*"),
    MoveBattalion("\\s*move\\s+battalion\\s+(?<index>-?\\d+)\\s+(?<name>\\S+)\\s+(?<destIndex>-?\\d+)\\s*"),
    UpgradeBattalion("\\s*upgrade\\s+battalion\\s+(?<index>-?\\d+)\\s+(?<name>\\S+)\\s*"),

    CreateFaction("\\s*create\\s+faction\\s+(?<name>.+)\\s*"),
    JoinFaction("\\s*join\\s+faction\\s+(?<name>.+)\\s*"),
    LeaveFaction("\\s*leave\\s+faction\\s+(?<name>.+)\\s*"),

    BuildFactory("\\s*build\\s+factory\\s+(?<index>-?\\d+)\\s+(?<factoryType>.+)\\s+(?<name>\\S+)\\s*"),
    RunFactory("\\s*run\\s+factory\\s+(?<index>-?\\d+)\\s+(?<name>\\S+)\\s+(?<manPowerCount>-?\\d+)\\s*"),

    Attack("\\s*attack\\s+(?<myIndex>-?\\d+)\\s+(?<enemyIndex>-?\\d+)\\s+(?<battalionType>\\S+)\\s*"),
    StartCivilWar("\\s*start\\s+civil\\s+war\\s+(?<tile1>-?\\d+)\\s+(?<tile2>-?\\d+)\\s+(?<battalionType>\\S+)\\s*"),

    Puppet("\\s*puppet\\s+(?<countryName>.+)\\s*"),

    StartElection("\\s*start\\s+election\\s*"),

    FinishGame("\\s*sadagha\\s+allah\\s+ol\\s+aliol\\s+azim\\s*");

    private final String pattern;

    GameMenuCommands(String pattern) {
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
