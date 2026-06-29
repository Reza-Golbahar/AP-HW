package models.enums;

import views.*;

import java.util.Scanner;

public enum Menu {
    MainMenu(new MainMenu()),
    SignUpMenu(new SignUpMenu()),
    LoginMenu(new LoginMenu()),
    GameMenu(new GameMenu()),
    LeaderBoard(new LeaderBoard()),
    ExitMenu(new ExitMenu());

    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void check(Scanner scanner) {
        this.menu.check(scanner);
    }
}
