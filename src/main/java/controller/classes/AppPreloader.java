package controller.classes;

import javafx.application.Preloader.StateChangeNotification.Type;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;
import util.gui.FXUtils;
import util.gui.i18n.*;
import java.util.ResourceBundle;


public class AppPreloader extends Preloader {

    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            this.preloaderStage = primaryStage;
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(getClass().getResource("/view/preloadStartScreen.fxml"));
            loader.setResources(bundle);
            Pane pane = loader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.getIcons().add(new Image("images/cd.png"));
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.TRANSPARENT);

            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
            FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorAppCantStart(), ButtonType.OK);
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == Type.BEFORE_START) {
            preloaderStage.hide();
        }
    }
}