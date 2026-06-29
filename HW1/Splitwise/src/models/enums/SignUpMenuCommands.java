package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Explanation:
- we have commands in our sign-up menu and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */


public enum SignUpMenuCommands implements Command {
    Register("\\s*register\\s+-u\\s+(?<username>.+)\\s+-p\\s+(?<password>.+)\\s+-e\\s+" +
            "(?<email>.+)\\s+-n\\s+(?<name>.+)\\s*"),
    Username("[a-zA-Z][a-zA-Z0-9_.-]{3,9}"),
    Password("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,12}$"),
    Email("[a-zA-Z][a-zA-Z0-9_.-]{3,9}@[a-z](?=.*[-.]{0,1})[a-z-.]{1,5}[a-z]\\.(com|net|org|edu)"),
    Name("[a-zA-Z]+(-[a-zA-Z]+)*"),
    GoToLoginMenu("\\s*go\\s+to\\s+login\\s+menu\\s*"),
    EXIT("\\s*exit\\s*");

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