package com.taskboard;

public class Main {
    public static void main(String[] args) {
        Database.initialize();
        new BoardManager().showMainMenu();
    }
}
