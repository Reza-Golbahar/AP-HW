package controllers;

import models.*;
import models.enums.Currency;
import models.enums.DashboardCommands;
import models.enums.GroupType;
import models.enums.Menu;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/*
Explanation:
- This is a controller class for the dashboard Controller.
- This class will be used to implement functions that do dashboard operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

public class DashboardController {

    public Result createGroup(String groupName, String groupType) {
        if ((DashboardCommands.GroupName.getMatcher(groupName) == null)) {
            return new Result(false, "group name format is invalid!");
        } else if (DashboardCommands.GroupType.getMatcher(groupType) == null) {
            return new Result(false, "group type is invalid!");
        }

        GroupType groupType1 = null;
        if (groupType.equals(GroupType.FAMILY.getDisplayName())) {
            groupType1 = GroupType.FAMILY;
        } else if (groupType.equals(GroupType.HOME.getDisplayName())) {
            groupType1 = GroupType.HOME;
        } else if (groupType.equals(GroupType.TRIP.getDisplayName())) {
            groupType1 = GroupType.TRIP;
        } else if (groupType.equals(GroupType.OTHER.getDisplayName())) {
            groupType1 = GroupType.OTHER;
        }

        Group newGroup = new Group(App.getLoggedInUser(), groupName, groupType1);
        App.getLoggedInUser().getGroups().add(newGroup);
        App.getAllGroups().add(newGroup);
        return new Result(true, "group created successfully!");
    }


    public Result showMyGroups() {
        StringBuilder result = new StringBuilder();
        int counter = 0;
        for (Group group : App.getLoggedInUser().getGroups()) {
            result
                    .append("group name : ")
                    .append(group.getGroupName() + "\n")
                    .append("id : ")
                    .append(group.getGroupID() + "\n")
                    .append("type : ")
                    .append(group.getGroupType().getDisplayName() + "\n")
                    .append("creator : ")
                    .append(group.getCreator().getName() + "\n")
                    .append("members :\n");

            for (User user : group.getUsers()) {
                result.append(user.getName() + "\n");
            }

            result.append("--------------------");
            if (counter < App.getLoggedInUser().getGroups().size() - 1) result.append("\n");
            counter++;
        }

        return new Result(true, result.toString());
    }


    public Result addUser(String username, String email, String groupID) {
        User userToBeAdded = getUserByUsername(username);
        Group groupUnderAddingOperation = App.getGroupByGroupID(Integer.parseInt(groupID));

        if (userToBeAdded == null) {
            return new Result(false, "user not found!");
        } else if (groupUnderAddingOperation == null) {
            return new Result(false, "group not found!");
        }

        if (groupUnderAddingOperation.isContainingUsernameGiven(username)) {
            return new Result(false, "user already in the group!");
        }


        if (!userToBeAdded.getEmail().equals(email)) {
            return new Result(false, "the email provided does not match the username!");
        } else if (!groupUnderAddingOperation.getCreator().equals(App.getLoggedInUser())) {
            return new Result(false, "only the group creator can add users!");
        }

        groupUnderAddingOperation.getUsers().add(userToBeAdded);
        userToBeAdded.getGroups().add(groupUnderAddingOperation);
        return new Result(true, "user added to the group successfully!");
    }


    public Result addExpense(Scanner scanner, Matcher matcher) {
        String groupID = matcher.group("groupID").trim();
        String equality = matcher.group("equality").trim();
        String totalExpense = matcher.group("totalExpense").trim();
        String numberOfUsers = matcher.group("numberOfUsers").trim();

        LinkedHashMap<String, String> usernamesAndExpenses = parseUserExpenses(scanner, equality, Integer.parseInt(numberOfUsers));
        return processExpense(groupID, equality, totalExpense, numberOfUsers, usernamesAndExpenses);
    }

    private LinkedHashMap<String, String> parseUserExpenses(Scanner scanner, String equality, int numberOfUsers) {
        LinkedHashMap<String, String> usernamesAndExpenses = new LinkedHashMap<>();

        for (int i = 0; i < numberOfUsers; i++) {
            String input = scanner.nextLine();
            Matcher matcherForUser;

            if (equality.equals("equally")) {
                matcherForUser = DashboardCommands.UsersAndExpensesEqually.getMatcher(input);
                usernamesAndExpenses.put(matcherForUser.group("username").trim(), "");
            } else if (equality.equals("unequally")) {
                matcherForUser = DashboardCommands.UsersAndExpensesUnequally.getMatcher(input);
                usernamesAndExpenses.put(
                        matcherForUser.group("username").trim(),
                        matcherForUser.group("expense").trim()
                );
            }
        }

        return usernamesAndExpenses;
    }

    private Result processExpense(String groupID, String equality, String totalExpense, String numberOfUsers,
                                  LinkedHashMap<String, String> usernamesAndExpenses) {
        Group groupUnderOperation = App.getGroupByGroupID(Integer.parseInt(groupID));
        if (groupUnderOperation == null){
            return new Result(false, "group not found!");
        }
        boolean isContainingAllUsers = true;
        StringBuilder result = new StringBuilder();
        for (String username : usernamesAndExpenses.keySet()) {
            if (username.equals(App.getLoggedInUser().getUsername()))
                continue;

            if (!groupUnderOperation.isContainingUsernameGiven(username)) {
                isContainingAllUsers = false;
                result.append(username + " not in group!\n");
            }
        }
        if (!isContainingAllUsers) {
            String result1 = result.substring(0, result.length() - 1);
            return new Result(false, result1);
        }

        if (DashboardCommands.Expense.getMatcher(totalExpense) == null) {
            return new Result(false, "expense format is invalid!");
        } else if (equality.equals("unequally")) {
            int sumOfIndividualCosts = 0;
            for (String value : usernamesAndExpenses.values()) {
                if (DashboardCommands.Expense.getMatcher(value) == null) {
                    return new Result(false, "expense format is invalid!");
                }
                sumOfIndividualCosts += Integer.parseInt(value);
            }
            if (sumOfIndividualCosts != Integer.parseInt(totalExpense)) {
                return new Result(false, "the sum of individual costs does not equal the total cost!");
            }
        }
        for (String username : usernamesAndExpenses.keySet()) {
            if (username.equals(App.getLoggedInUser().getUsername()))
                continue;

            Expense expense = groupUnderOperation.isContainingExpense(
                    App.getLoggedInUser().getUsername(), username);

            long currentExpenseAmount;
            if (equality.equals("unequally")) {
                currentExpenseAmount = Integer.parseInt(usernamesAndExpenses.get(username));
            } else {
                currentExpenseAmount = Integer.parseInt(totalExpense) / Integer.parseInt(numberOfUsers);
            }
            currentExpenseAmount = changeCurrency(currentExpenseAmount,
                    App.getLoggedInUser().getDefaultCurrency(),
                    Currency.GTC);

            if (expense != null) {
                expense.setAmount(expense.getAmount() + currentExpenseAmount);
                continue;
            }
            expense = groupUnderOperation.isContainingExpense(
                    username, App.getLoggedInUser().getUsername());
            if (expense != null) {
                expense.setAmount(expense.getAmount() - currentExpenseAmount); //in this case we subtract
                continue;
            }

            //no expense between these users exist
            Expense newExpense = new Expense(currentExpenseAmount,
                    App.getLoggedInUser(),
                    App.giveUserByUsername(username));
            groupUnderOperation.getExpenses().add(newExpense);
        }

        return new Result(true, "expense added successfully!");
    }




    public Result showBalance(String username) {
        User user = App.giveUserByUsername(username);
        if (user == null) {
            return new Result(false, "user not found!");
        }

        User loggedInUser = App.getLoggedInUser();

        ArrayList<Group> commonGroups = new ArrayList<>();
        long totalExpense = 0;

        for (Group group : loggedInUser.getGroups()) {
            if (group == null) continue;

            if (group.getUsers().contains(user)) {
                commonGroups.add(group);
            }

            for (Expense expense : group.getExpenses()) {
                if (expense == null) continue;

                if (expense.getCreditor().equals(loggedInUser) && expense.getDebtor().equals(user)) {
                    totalExpense += changeCurrency(expense.getAmount(), Currency.GTC, loggedInUser.getDefaultCurrency());
                } else if (expense.getDebtor().equals(loggedInUser) && expense.getCreditor().equals(user)) {
                    totalExpense -= changeCurrency(expense.getAmount(), Currency.GTC, loggedInUser.getDefaultCurrency());
                }
            }
        }

        if (totalExpense == 0) {
            return new Result(true, "you are settled with " + username);
        }

        String status = totalExpense < 0 ? "you owe " + username : username + " owes you";
        totalExpense = Math.abs(totalExpense);

        String groupNames = commonGroups.stream().map(Group::getGroupName).collect(Collectors.joining(", "));
        return new Result(true, status + " " + totalExpense + " " + loggedInUser.getDefaultCurrency().name() + " in " + groupNames + "!");
    }


    public Result settleUp(String username, String inputMoney) {
        if (DashboardCommands.Expense.getMatcher(inputMoney) == null) {
            return new Result(false, "input money format is invalid!");
        }

        User user = App.giveUserByUsername(username);
        if (user == null) {
            return new Result(false, "user not found!");
        }

        User loggedInUser = App.getLoggedInUser();

        if (username.equals(App.getLoggedInUser().getUsername())) {
            return new Result(true, "you are settled with %s now!".formatted(username));
        }

        long totalExpense = changeCurrency(Long.parseLong(inputMoney), loggedInUser.getDefaultCurrency(), Currency.GTC);
        Group lastCommonGroup = null;

        for (Group group : loggedInUser.getGroups()) {
            if (group == null || !group.getUsers().contains(user)) continue;
            if (lastCommonGroup == null)
                lastCommonGroup = group;
            else if (group.getGroupID() > lastCommonGroup.getGroupID())
                lastCommonGroup = group;

            for (Expense expense : group.getExpenses()) {
                if (expense == null) continue;

                if (expense.getCreditor().equals(loggedInUser) && expense.getDebtor().equals(user)) {
                    totalExpense += expense.getAmount();
                    expense.setAmount(0);
                } else if (expense.getDebtor().equals(loggedInUser) && expense.getCreditor().equals(user)) {
                    totalExpense -= expense.getAmount();
                    expense.setAmount(0);
                }
            }
        }

        if (totalExpense == 0) {
            return new Result(true, "you are settled with " + username + " now!");
        }

        if (lastCommonGroup != null)
            lastCommonGroup.getExpenses().add(new Expense(totalExpense, loggedInUser, user));
        else
            return new Result(true, "you are settled with %s now!".formatted(username));

        totalExpense = changeCurrency(
                totalExpense,
                Currency.GTC,
                App.getLoggedInUser().getDefaultCurrency()
        );

        String status = totalExpense < 0 ? "you owe " + username : username + " owes you";
        return new Result(true, status + " " + Math.abs(totalExpense) + " " + loggedInUser.getDefaultCurrency() + " now!");
    }


    public Result goToProfileMenu() {
        App.setCurrentMenu(Menu.ProfileMenu);
        return new Result(true, "you are now in profile menu!");
    }


    public Result logout() {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "user logged out successfully.you are now in login menu!");
    }


    public User getUserByUsername(String username) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


    public long changeCurrency(long firstAmount, Currency currency1, Currency currency2) {
        // Convert firstAmount to GTC
        long amountInGTC = firstAmount * currency1.getRelationalValue();

        // Convert GTC to target currency
        return amountInGTC / currency2.getRelationalValue();
    }


    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }
}