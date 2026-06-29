package models;

import models.enums.Menu;

import java.util.ArrayList;

/*
Explanation:
- In out app, we need somewhere to keep our general data like list of users and list of groups and logged-in user etc.
- This class is the place for that.
- Put your general data here and use them in your code.
- you should put some functions here to manage your data too.
 */
public class App {
    private static Menu currentMenu = Menu.SignUpMenu;
    private static ArrayList<User> users = new ArrayList<>();
    private static User loggedInUser;
    private static ArrayList<Group> allGroups = new ArrayList<>();

    public static Group getGroupByGroupID(int groupID) {
        for (Group group : allGroups) {
            if (group.getGroupID() == (groupID)) {
                return group;
            }
        }
        return null;
    }

    public static User giveUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static ArrayList<Group> getAllGroups() {
        return allGroups;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        App.users = users;
    }


}
