package controllers;

import models.App;
import models.Result;
import models.User;
import models.enums.Menu;
import models.enums.SignUpMenuCommands;

public class SignUpMenuController {

    public Result showCurrentMenu() {
        return new Result(true, "%s".formatted(App.giveCurrentMenuName()));
    }


    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }


    public Result register(String username, String password, String email) {
        if (SignUpMenuCommands.UserName.getMatcher(username) == null) {
            return new Result(false, "invalid username");
        }

        for (User user : App.getAllUsers()) {
            if (user.getUsername().equals(username)) {
                return new Result(false, "username is already taken");
            }
        }

        if (password.length() > 20 || password.length() < 8) {
            return new Result(false, "invalid length");
        } else if (password.matches(".*\\s.*")) {
            return new Result(false, "don't use whitespace in password");
        } else if (!password.substring(0, 1).matches("[a-zA-Z]")) {
            return new Result(false, "password must start with English letters");
        } else if (!password.matches(".*[%@#$^&!].*")) {
            return new Result(false, "password doesn't have special characters");
        } else if ((SignUpMenuCommands.Email.getMatcher(email) == null) ||
                SignUpMenuCommands.Email.getMatcher(email).group("mail").isEmpty()) {
            return new Result(false, "invalid email format");
        }

        App.setCurrentMenu(Menu.LoginMenu);
        App.getAllUsers().add(new User(username, email, password));
        return new Result(true, "user registered successfully");
    }


    public void login() {
        App.setCurrentMenu(Menu.LoginMenu);
    }
}
