package controllers;

/*
Explanation:
- This is a controller class for the profile menu Controller.
- This class will be used to implement functions that do profile menu operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

import models.App;
import models.Result;
import models.User;
import models.enums.Currency;
import models.enums.Menu;
import models.enums.SignUpMenuCommands;

public class ProfileMenuController {

    public Result showUserInfo(){
        StringBuilder result = new StringBuilder();
        result
                .append("username : " + App.getLoggedInUser().getUsername())
                .append("\npassword : " + App.getLoggedInUser().getPassword())
                .append("\ncurrency : " + App.getLoggedInUser().getDefaultCurrency().name())
                .append("\nemail : " + App.getLoggedInUser().getEmail())
                .append("\nname : " + App.getLoggedInUser().getName());

        return new Result(true, result.toString());
    }


    public Result changeCurrency(String newCurrency){
        newCurrency = newCurrency.trim();

        Currency newCurrency1 = null;
        if (newCurrency.equals(Currency.GTC.name())){
            newCurrency1 = Currency.GTC;
        } else if (newCurrency.equals(Currency.SUD.name())){
            newCurrency1 = Currency.SUD;
        } else if (newCurrency.equals(Currency.QTR.name())) {
            newCurrency1 = Currency.QTR;
        }

        if (newCurrency1 == null){
            return new Result(false, "currency format is invalid!");
        }

        App.getLoggedInUser().setDefaultCurrency(newCurrency1);
        return new Result(true, "your currency changed to "
                + newCurrency1.name()
                + " successfully!");
    }


    public Result changeUsername(String newUsername){
        newUsername = newUsername.trim();

        if (newUsername.equals(App.getLoggedInUser().getUsername())){
            return new Result(false, "please enter a new username!");
        }

        for (User user : App.getUsers()) {
            if (user.getUsername().equals(newUsername)){
                return new Result(false, "this username is already taken!");
            }
        }

        if (SignUpMenuCommands.Username.getMatcher(newUsername) == null){
            return new Result(false, "new username format is invalid!");
        }

        App.getLoggedInUser().setUsername(newUsername);
        return new Result(true, "your username changed to "
                + newUsername
                + " successfully!");
    }


    public Result changePassword(String oldPassword, String newPassword){
        oldPassword = oldPassword.trim();
        newPassword = newPassword.trim();

        if (!App.getLoggedInUser().getPassword().equals(oldPassword)){
            return new Result(false, "password incorrect!");
        } else if (oldPassword.equals(newPassword)){
            return new Result(false, "please enter a new password!");
        } else if (SignUpMenuCommands.Password.getMatcher(newPassword) == null){
            return new Result(false, "new password format is invalid!");
        }
        App.getLoggedInUser().setPassword(newPassword);
        return new Result(true, "your password changed successfully!");
    }


    public Result backToDashBoard(){
        App.setCurrentMenu(Menu.Dashboard);
        return new Result(true, "you are now in dashboard!");
    }


    public void exit(){
        App.setCurrentMenu(Menu.ExitMenu);
    }
}
