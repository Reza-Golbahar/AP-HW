package models.enums;

public enum LeaderType {
    Adenauer(Ideology.Democracy, CountryType.Germany),
    Pieck(Ideology.Communism, CountryType.Germany),
    Hitler(Ideology.Fascism, CountryType.Germany),

    Lenin(Ideology.Democracy, CountryType.SovietUnion),
    Stalin(Ideology.Communism, CountryType.SovietUnion),
    Trotsky(Ideology.Fascism, CountryType.SovietUnion),

    Roosevelt(Ideology.Democracy, CountryType.USA),
    Browder(Ideology.Communism, CountryType.USA),
    Pelley(Ideology.Fascism, CountryType.USA),

    Churchill(Ideology.Democracy, CountryType.UK),
    Mosley(Ideology.Fascism, CountryType.UK),

    Hirohito(Ideology.Fascism, CountryType.Japan);

    private final Ideology ideology;
    private final CountryType country;

    LeaderType(Ideology ideology, CountryType country) {
        this.ideology = ideology;
        this.country = country;
    }

    public String getName() {
        if (this.name().equals("Lenin")) {
            return "Zombie-Lenin";
        }
        return this.name();
    }

    public Ideology getIdeology() {
        return ideology;
    }

    public CountryType getCountryType() {
        return country;
    }
}

