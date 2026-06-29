package views;

/*
Explanation:
- This is a view class for the App.
- our app needs a place that handle menus (:
 */

import java.util.Scanner;

import models.App;
import models.enums.Menu;

public class AppView {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != Menu.ExitMenu);
    }
}
