package views;

import models.App;
import models.enums.Menu;

import java.util.Scanner;

public class AppView {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            App.getCurrentMenu().check(scanner);
        } while (App.getCurrentMenu() != Menu.ExitMenu);
    }
}
