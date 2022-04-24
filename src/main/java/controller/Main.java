package controller;

import com.sun.javafx.application.LauncherImpl;

public class Main {
    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainApp.class, AppPreloader.class, args);
    }
}
