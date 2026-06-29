package controllers;

import models.App;
import models.Result;
import models.User;
import models.enums.Menu;

public class LoginMenuController {

    public Result showCurrentMenu() {
        return new Result(true, "%s".formatted(App.giveCurrentMenuName()));
    }


    public void back() {
        App.setCurrentMenu(Menu.SignUpMenu);
    }


    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }


    public Result login(String username, String password) {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return new Result(false, "username doesn't exist!");
        } else if (!user.getPassword().equals(password)) {
            return new Result(false, "password is incorrect!");
        }

        App.setLoggedInUser(user);
        App.setCurrentMenu(Menu.MainMenu);
        return new Result(true, "user logged in successfully");
    }


    public Result forgetPassword(String username, String email) {
        User user = App.getUserByUsername(username);
        if (user == null) {
            return new Result(false, "username doesn't exist!");
        } else if (!user.getEmail().equals(email)) {
            return new Result(false, "email doesn't match!");
        }

        return new Result(true, "password: %s".formatted(user.getPassword()));
    }

}
