package models;

import models.enums.Menu;

import java.util.ArrayList;
import java.util.List;

public class App {
    private static Menu currentMenu = Menu.SignUpMenu;
    private static User loggedInUser;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static Game runningGame;
    private static List<GameHistoryEntry> gameHistory = new ArrayList<>();

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static String giveCurrentMenuName() {
        if (App.getCurrentMenu() == Menu.SignUpMenu) {
            return "signup menu";
        } else if (App.getCurrentMenu() == Menu.LoginMenu) {
            return "login menu";
        } else if (App.getCurrentMenu() == Menu.MainMenu) {
            return "main menu";
        } else if (App.getCurrentMenu() == Menu.GameMenu) {
            return "game menu";
        }
        return "leaderboard menu";
    }

    public static List<GameHistoryEntry> getGameHistory() {
        return gameHistory;
    }

    public static Game getRunningGame() {
        return runningGame;
    }

    public static void setRunningGame(Game runningGame) {
        App.runningGame = runningGame;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        App.allUsers = allUsers;
    }


    public static User getUserByUsername(String username) {
        for (User user : App.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
