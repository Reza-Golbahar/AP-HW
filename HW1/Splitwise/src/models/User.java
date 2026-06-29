package models;

/*
Explanation:
- User is definitely an object in our app.
- put the information that you need to store about the user here.
- you can put some functions here to manage the user data too.
 */

import models.enums.Currency;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String email;
    private String name;
    private ArrayList<Group> groups = new ArrayList<>();
    private Currency defaultCurrency = Currency.GTC;


    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(name, user.name);
    }
}
