package controllers;

import models.App;
import models.Market;
import models.Result;
import models.User;
import models.enums.Menu;

public class MainMenuController {

    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }


    public Result goToLoginMenu() {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "Redirecting to the LoginMenu ...");
    }


    public Result goToProductMenu() {
        App.setCurrentMenu(Menu.ProductMenu);
        return new Result(true, "Redirecting to the ProductMenu ...");
    }


    public Result goToStoreMenu() {
        if (!checkStoreLogin()) {
            return new Result(false, "You should login as store before accessing the store menu.");
        }
        App.setCurrentMenu(Menu.StoreMenu);
        return new Result(true, "Redirecting to the StoreMenu ...");
    }


    public Result goToUserMenu() {
        if (!checkUserLogin()) {
            return new Result(false, "You need to login as a user before accessing the user menu.");
        }
        App.setCurrentMenu(Menu.UserMenu);
        return new Result(true, "Redirecting to the UserMenu ...");
    }

    public static boolean checkStoreLogin() {
        if (App.getLoggedInUserOrMarket() == null) {
            return false;
        }
        try {
            Market loggedInMarket = (Market) App.getLoggedInUserOrMarket();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean checkUserLogin() {
        if (App.getLoggedInUserOrMarket() == null) {
            return false;
        }
        try {
            User loggedInMarket = (User) App.getLoggedInUserOrMarket();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
