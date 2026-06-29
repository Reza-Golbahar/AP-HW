package controllers;
/*
Explanation:
- This is a controller class for the sign-up menu Controller.
- This class will be used to implement functions that do sign up menu operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

import models.App;
import models.Result;
import models.User;
import models.enums.Menu;
import models.enums.SignUpMenuCommands;

public class SignUpMenuController {

    public Result register(String username, String password, String email, String name) {
        if (SignUpMenuCommands.Username.getMatcher(username) == null) {
            return new Result(false, "username format is invalid!");
        }

        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username))
                return new Result(false, "this username is already taken!");
        }

        if (SignUpMenuCommands.Password.getMatcher(password) == null) {
            return new Result(false, "password format is invalid!");
        } else if (SignUpMenuCommands.Email.getMatcher(email) == null) {
            return new Result(false, "email format is invalid!");
        } else if (SignUpMenuCommands.Name.getMatcher(name) == null) {
            return new Result(false, "name format is invalid!");
        }

        App.getUsers().add(new User(username, password, email, name));
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "user registered successfully.you are now in login menu!");
    }


    public Result goToLoginMenu() {
        App.setCurrentMenu(Menu.LoginMenu);
        return new Result(true, "you are now in login menu!");
    }

    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }
}
