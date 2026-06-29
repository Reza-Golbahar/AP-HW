package models;

import models.enums.Menu;

import java.util.ArrayList;

public class App {
    private static Menu currentMenu = Menu.MainMenu;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static ArrayList<Market> allMarkets = new ArrayList<>();
    private static Logginable loggedInUserOrMarket = null;
    private static int nextProductToBeAddedID = 101;

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static int getNextProductToBeAddedID() {
        return nextProductToBeAddedID;
    }

    public static void setNextProductToBeAddedID(int nextProductToBeAddedID) {
        App.nextProductToBeAddedID = nextProductToBeAddedID;
    }

    public static ArrayList<Market> getAllMarkets() {
        return allMarkets;
    }

    public static Logginable getLoggedInUserOrMarket() {
        return loggedInUserOrMarket;
    }

    public static void setLoggedInUserOrMarket(Logginable loggedInUserOrMarket) {
        App.loggedInUserOrMarket = loggedInUserOrMarket;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menu currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static Product getProductByID(int productID) {
        for (Market market : App.getAllMarkets()) {
            for (Product product : market.getListOfProductsAndCount().keySet()) {
                if (product.getProductID() == productID) {
                    return product;
                }
            }
        }
        return null;
    }
}
