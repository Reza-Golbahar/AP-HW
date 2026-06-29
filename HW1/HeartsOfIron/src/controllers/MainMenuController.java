package controllers;

import models.*;
import models.enums.Menu;

import java.util.*;

public class MainMenuController {

    public Result showCurrentMenu() {
        return new Result(true, "%s".formatted(App.giveCurrentMenuName()));
    }


    public void logout() {
        App.setCurrentMenu(Menu.SignUpMenu);
        App.setLoggedInUser(null);
    }


    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }


    public void leaderboard() {
        App.setCurrentMenu(Menu.LeaderBoard);
    }


    public Result play(String[] playerNames) {
        for (String playerName : playerNames) {
            if (playerName == null) continue;
            User user = App.getUserByUsername(playerName);
            if (user == null) {
                return new Result(false, "select between available users");
            }
        }

        for (int index = 0; index < playerNames.length; index++) {
            if (playerNames[index] == null) continue;

            for (int index2 = index + 1; index2 < playerNames.length; index2++) {
                if (playerNames[index2] == null) continue;

                if (playerNames[index].equals(playerNames[index2]))
                    return new Result(false, "users must be distinct");
            }
        }

        for (String playerName : playerNames) {
            if (playerName == null) continue;
            if (playerName.equals(App.getLoggedInUser().getUsername()))
                return new Result(false, "you can't choose urself");
        }

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(App.getLoggedInUser(), false));

        int guestNumber = 1;
        for (String playerName : playerNames) {
            if (playerName == null) {
                User guest = new User("guest%d".formatted(guestNumber++), "", "");
                players.add(new Player(guest, true));
            } else {
                players.add(new Player(App.getUserByUsername(playerName), false));
            }
        }

        Game newGame = new Game(players);

        App.setRunningGame(newGame);

        App.setCurrentMenu(Menu.GameMenu);
        return new Result(true, "aghaaz faAliat");
    }
}
