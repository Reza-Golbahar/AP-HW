package models.enums;

/*
Explanation:
- In our app, groups have some types that are constants.
- In these cases, we use enums to define them and use them in our code.
- put those types here and use them in your code.
 */

public enum GroupType {
    TRIP("Trip"),
    HOME("Home"),
    FAMILY("Zan-o-Bache"),
    OTHER("Other");

    private final String displayName;

    GroupType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

