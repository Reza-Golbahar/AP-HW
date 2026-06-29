package models;

import models.enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu = Menu.MainMenu;
    private static int moneyLeft = 1000000000;
    private static ArrayList<Athlete> allAthletes = new ArrayList<>();

    public static Athlete findAthlete(String name) {
        for (Athlete athlete : allAthletes) {
            if (athlete.getName().equals(name)) {
                return athlete;
            }
        }
        return null;
    }

    public static ArrayList<Athlete> getAllAthletes() {
        return allAthletes;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static int getMoneyLeft() {
        return moneyLeft;
    }

    public static void addMoney(int amount) {
        moneyLeft += amount;
    }

    public static void spendMoney(int amount) {
        moneyLeft -= amount;
    }
}
