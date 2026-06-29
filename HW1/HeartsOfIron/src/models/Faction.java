package models;

import java.util.HashSet;
import java.util.Set;

public class Faction {
    private String name;
    private Set<Country> members;

    public Faction(String name) {
        this.name = name;
        this.members = new HashSet<>();
    }

    public void addMember(Country country) {
        members.add(country);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Country> getMembers() {
        return members;
    }

    public void setMembers(Set<Country> members) {
        this.members = members;
    }
}

