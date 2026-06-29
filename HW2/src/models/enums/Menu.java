package models.enums;

import views.AppMenu;
import views.ExitMenu;
import views.MainMenu;

import java.util.Scanner;

public enum Menu {
    MainMenu(new MainMenu()),
    ExitMenu(new ExitMenu());

    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }
}
