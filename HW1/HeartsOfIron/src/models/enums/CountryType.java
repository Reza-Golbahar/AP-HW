package models.enums;

public enum CountryType {
    SovietUnion("Soviet Union"),
    USA("United States"),
    Germany("German Reich"),
    Japan("Japan"),
    UK("United Kingdom");

    private final String displayName;

    CountryType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

