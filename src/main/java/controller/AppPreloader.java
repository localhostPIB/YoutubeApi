package controller;

import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.gui.i18n.I18nUtil;

import java.util.ResourceBundle;

public class AppPreloader extends Preloader {

    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
        loader.setLocation(getClass().getResource("/view/preloadStartScreen.fxml"));
        loader.setResources(bundle);
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}