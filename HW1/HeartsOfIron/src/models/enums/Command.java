package models.enums;

import java.util.regex.Matcher;

public interface Command {
    Matcher getMatcher(String input);
}
