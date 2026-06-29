package controllers;
/*
Explanation:
- This is a controller class for the login menu Controller.
- This class will be used to implement functions that do log in menu operations.
- notice that this class should not have any input and output and just use it to implement functionalities.
 */

import models.App;
import models.Result;
import models.User;
import models.enums.Menu;

public class LoginMenuController {

    public Result login(String username, String password) {
        username = username.trim();
        password = password.trim();

        User user = getUserByUsername(username);
        if (user == null) {
            return new Result(false, "username doesn't exist!");
        }

        if (!user.getPassword().equals(password)) {
            return new Result(false, "password is incorrect!");
        }

        App.setLoggedInUser(user);
        App.setCurrentMenu(Menu.Dashboard);
        return new Result(true, "user logged in successfully.you are now in dashboard!");
    }

    public Result forgetPassword(String username, String email) {
        username = username.trim();
        email = email.trim();


        User user = getUserByUsername(username);
        if (user == null) {
            return new Result(false, "username doesn't exist!");
        }

        if (!user.getEmail().equals(email)) {
            return new Result(false, "email doesn't match!");
        }

        return new Result(true, "password : " + user.getPassword());
    }

    public Result goToSignUpMenu() {
        App.setCurrentMenu(Menu.SignUpMenu);
        return new Result(true, "you are now in signup menu!");
    }


    public User getUserByUsername(String username) {
        for (User user : App.getUsers()) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }


    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }
}
