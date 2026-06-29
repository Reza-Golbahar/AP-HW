package controllers;

import models.*;
import models.enums.LoginMenuCommands;
import models.enums.Menu;

public class LoginMenuController {

    public Result createUserAccount(String firstName,
                                    String lastName,
                                    String password,
                                    String reEnteredPassword,
                                    String emailAddress) {
        firstName = firstName.trim();
        lastName = lastName.trim();
        password = password.trim();
        reEnteredPassword = reEnteredPassword.trim();
        emailAddress = emailAddress.trim();


        if ((firstName.length() < 3) || (lastName.length() < 3)) {
            return new Result(false, "Name is too short.");
        } else if ((LoginMenuCommands.Name.getMatcher(firstName) == null) ||
                (LoginMenuCommands.Name.getMatcher(lastName)) == null) {
            return new Result(false, "Incorrect name format.");
        } else if (LoginMenuCommands.Password.getMatcher(password) == null) {
            return new Result(false, "Incorrect password format.");
        } else if (!password.equals(reEnteredPassword)) {
            return new Result(false, "Re-entered password is incorrect.");
        } else if (LoginMenuCommands.Email.getMatcher(emailAddress) == null) {
            return new Result(false, "Incorrect email format.");
        }

        for (User user : App.getAllUsers()) {
            if (user.getEmailAddress().equals(emailAddress)) {
                return new Result(false, "Email already exists.");
            }
        }

        for (Market market : App.getAllMarkets()) {
            if (market.getEmailAddress().equals(emailAddress)) {
                return new Result(false, "Email already exists.");
            }
        }

        User newUser = new User(firstName, lastName, password, emailAddress);
        App.getAllUsers().add(newUser);
        return new Result(true, "User account for " +
                firstName + " " + lastName + " created successfully.");
    }


    public Result createMarketAccount(String brand, String password,
                                      String reEnteredPassword, String emailAddress) {
        //brand must not be trimmed
        password = password.trim();
        reEnteredPassword = reEnteredPassword.trim();
        emailAddress = emailAddress.trim();

        if (brand.length() < 3) {
            return new Result(false, "Brand name is too short.");
        } else if (LoginMenuCommands.Password.getMatcher(password) == null) {
            return new Result(false, "Incorrect password format.");
        } else if (!password.equals(reEnteredPassword)) {
            return new Result(false, "Re-entered password is incorrect.");
        } else if (LoginMenuCommands.Email.getMatcher(emailAddress) == null) {
            return new Result(false, "Incorrect email format.");
        }

        for (Market market : App.getAllMarkets()) {
            if (market.getEmailAddress().equals(emailAddress)) {
                return new Result(false, "Email already exists.");
            }
        }

        for (User user : App.getAllUsers()) {
            if (user.getEmailAddress().equals(emailAddress)) {
                return new Result(false, "Email already exists.");
            }
        }

        Market newMarket = new Market(brand, password, emailAddress);
        App.getAllMarkets().add(newMarket);
        return new Result(true, "Store account for \"" + brand + "\" created successfully.");
    }


    public Result loginAsUser(String emailAddress, String password) {
        emailAddress = emailAddress.trim();
        password = password.trim();

        User loggedInUser = null;
        for (User user : App.getAllUsers()) {
            if (user.getEmailAddress().equals(emailAddress)) {
                if (user.getPassword().equals(password)) {
                    loggedInUser = user;
                    App.setCurrentMenu(Menu.MainMenu);
                    App.setLoggedInUserOrMarket(loggedInUser);
                    return new Result(true, "User logged in successfully. Redirecting to the MainMenu ...");
                } else {
                    return new Result(false, "Password is incorrect.");
                }
            }
        }
        return new Result(false, "No user account found with the provided email.");
    }


    public Result loginAsMarket(String emailAddress, String password) {
        emailAddress = emailAddress.trim();
        password = password.trim();

        Market loggedInMarket = null;
        for (Market market : App.getAllMarkets()) {
            if (market.getEmailAddress().equals(emailAddress)) {
                if (market.getPassword().equals(password)) {
                    loggedInMarket = market;
                    App.setCurrentMenu(Menu.MainMenu);
                    App.setLoggedInUserOrMarket(loggedInMarket);

                    return new Result(true, "Store logged in successfully. Redirecting to the MainMenu ...");
                } else {
                    return new Result(false, "Password is incorrect.");
                }
            }
        }
        return new Result(false, "No store account found with the provided email.");
    }


    public Result logout() {
        if (App.getLoggedInUserOrMarket() == null) {
            return new Result(false, "You should login first.");
        }
        App.setLoggedInUserOrMarket(null);
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Logged out successfully. Redirecting to the MainMenu ...");
    }


    public Result deleteAccount(String password, String reEnteredPassword) {
        password = password.trim();
        reEnteredPassword = reEnteredPassword.trim();

        if (App.getLoggedInUserOrMarket() == null) {
            return new Result(false, "You should login first.");
        } else if (!password.equals(reEnteredPassword)) {
            return new Result(false, "Re-entered password is incorrect.");
        } else if (!password.equals(App.getLoggedInUserOrMarket().getPassword())) {
            return new Result(false, "Password is incorrect.");
        }

        for (User user : App.getAllUsers()) {
            if (user.getEmailAddress().equals(App.getLoggedInUserOrMarket().getEmailAddress())) {
                user.deleteAccount();
                App.setLoggedInUserOrMarket(null);
                App.getAllUsers().remove(user);
                App.setCurrentMenu(Menu.MainMenu);
                return new Result(true, "Account deleted successfully. Redirecting to the MainMenu ...");
            }
        }

        for (Market market : App.getAllMarkets()) {
            if (market.getEmailAddress().equals(App.getLoggedInUserOrMarket().getEmailAddress())) {
                market.deleteAccount();
                App.setLoggedInUserOrMarket(null);
                App.getAllMarkets().remove(market);
                App.setCurrentMenu(Menu.MainMenu);
                break;
            }
        }
        // the account will be definitely found so we can return here instead of the upper if block
        return new Result(true, "Account deleted successfully. Redirecting to the MainMenu ...");
    }


    public Result goBack() {
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "Redirecting to the MainMenu ...");
    }

}
