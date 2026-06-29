package models.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Explanation:
- we have commands in our dashboard and this commands need regexes to be checked.
- put those regexes here and use them in your code.
- this regexes need some functions, put those functions in here.
 */
public enum DashboardCommands implements Command {
    CreateGroup("\\s*create-group\\s+-n\\s+(?<groupName>.+)\\s+-t\\s+(?<groupType>.+)\\s*"),
    GroupName("[a-zA-Z0-9 !@#$%^&*]{4,30}"),
    GroupType("(Home|Trip|Zan-o-Bache|Other)"),
    ShowMyGroups("\\s*show\\s+my\\s+groups\\s*"),
    AddUser("\\s*add-user\\s+-u\\s+(?<username>.+)\\s+-e\\s+(?<email>.+)\\s+-g\\s+(?<groupID>-?\\d+)\\s*"),
    AddExpense("\\s*add-expense\\s+-g\\s+(?<groupID>-?\\d+)\\s+-s\\s+(?<equality>(equally|unequally))\\s+" +
            "-t\\s+(?<totalExpense>.+)\\s+-n\\s+(?<numberOfUsers>-?\\d+)\\s*"),
    UsersAndExpensesEqually("\\s*(?<username>\\S+)\\s*"),
    UsersAndExpensesUnequally("\\s*(?<username>\\S+)\\s+(?<expense>.+)\\s*"),
    Expense("(?<expense>-?\\d+)"),
    ShowBalance("\\s*show\\s+balance\\s+-u\\s+(?<username>.+)\\s*"),
    SettleUp("\\s*settle-up\\s+-u\\s+(?<username>.+)\\s+-m\\s+(?<inputMoney>.+)\\s*"),
    GoToProfileMenu("\\s*go\\s+to\\s+profile\\s+menu\\s*"),
    Logout("\\s*logout\\s*"),
    EXIT("\\s*exit\\s*");

    private final String pattern;

    DashboardCommands(String pattern) {
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
