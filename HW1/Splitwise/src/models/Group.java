package models;
/*
Explanation:
- In our app, we have groups that have some information.
- Group is something that we need to make it an object because it looks like an object (:
- put those information here and use them in your code.
 */

import models.enums.GroupType;

import java.util.ArrayList;

public class Group {
    static int nextGroupID = 1;

    private User creator;
    private ArrayList<User> users = new ArrayList<>();
    private int groupID;
    private GroupType groupType;
    private String groupName;
    private ArrayList<Expense> expenses = new ArrayList<>();


    public Group(User creator, String groupName, GroupType groupType) {
        this.creator = creator;
        this.groupName = groupName;
        this.groupType = groupType;
        this.groupID = nextGroupID;
        nextGroupID++;

        this.users.add(creator);
    }

    public Expense isContainingExpense(String username1, String username2) {
        for (Expense expense : expenses) {
            if (expense.getCreditor().equals(username1) && //talabkar
                    expense.getDebtor().equals(username2)) { //bedehkar
                return expense;
            }
        }
        return null;
    }

    public ArrayList<Expense> getExpenses() {
        return expenses;
    }

    public boolean isContainingUsernameGiven(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public GroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
