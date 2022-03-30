package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.interfaces.IVideoInfo;
import util.FileUtils;

import java.io.IOException;

public class MainApp extends Application {
    private AnchorPane rootLayout;

    private Stage primaryStage;

    public MainApp(){

    }

    @Override
    public void start(Stage stage) throws Exception {
        FileUtils.createDirectory("res");
        this.primaryStage = stage;
        this.primaryStage.setTitle("Youtube Comment Picker Alpha");
        this.primaryStage.setResizable(false);

        initRootLayout();
    }

    public void initRootLayout() throws IOException {
        try {
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(getClass().getResource("/view/root.fxml"));
              this.rootLayout = loader.load();
              loader.setRoot(new AnchorPane());
              Scene scene = new Scene(rootLayout);
              primaryStage.setScene(scene);
              RootController rootController = loader.getController();
              rootController.setMainApp(this);
              primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showVideoInfosLayout(IVideoInfo iVideoInfo) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/videoInfos.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Video");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            VideoInfoController videoInfoController = loader.getController();
            videoInfoController.setDialogStage(dialogStage);
            videoInfoController.setVideoInfo(iVideoInfo);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
