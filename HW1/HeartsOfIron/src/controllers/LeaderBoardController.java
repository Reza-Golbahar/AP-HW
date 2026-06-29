package controllers;

import models.*;
import models.enums.*;

import java.util.*;

public class LeaderBoardController {

    public Result showCurrentMenu() {
        return new Result(true, "%s".formatted(App.giveCurrentMenuName()));
    }


    public void back() {
        App.setCurrentMenu(Menu.MainMenu);
    }


    public void exit() {
        App.setCurrentMenu(Menu.ExitMenu);
    }


    public Result showRanking() {
        List<User> rankedUsers = new ArrayList<>(App.getAllUsers());
        rankedUsers.sort(Comparator.comparing(User::getPoints).reversed()
                .thenComparing(User::getUsername, String.CASE_INSENSITIVE_ORDER));


        StringBuilder result = new StringBuilder("Leaderboard:\n");
        for (User user : rankedUsers) {
            result.append("%s %.0f\n".formatted(user.getUsername(),
                    user.getPoints()));

        }

        return new Result(true, result.toString().trim());
    }

    public Result showHistory() {
        StringBuilder result = new StringBuilder("History:\n");
        int count = 0;
        for (GameHistoryEntry entry : App.getGameHistory()) {
            if (count > 0) {
                result.append("----\n");
            }
            result.append(entry.toString()).append("\n");
            count++;
            if (count == 5) break;
        }

        return new Result(true, result.toString().trim());
    }

}
