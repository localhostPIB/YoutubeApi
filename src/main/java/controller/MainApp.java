package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.interfaces.IVideoInfo;
import service.classes.csv.CreateCSVFile;
import util.FileUtils;
import util.gui.i18n.I18nComponentsUtil;
import util.gui.i18n.I18nUtil;

import java.io.*;
import java.util.ResourceBundle;

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
            ResourceBundle bundle = I18nUtil.getComponentsResourceBundle();
            loader.setLocation(getClass().getResource("/view/root.fxml"));
            loader.setResources(bundle);
            this.rootLayout = loader.load();
            loader.setRoot(new AnchorPane());
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            RootController rootController = loader.getController();
            rootController.setMainApp(this);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSaveFiles() throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(I18nComponentsUtil.getLabelCsv());
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(I18nComponentsUtil.getLABELCSVFile(), "*.csv"));
        File file = fileChooser.showSaveDialog(primaryStage);

        if(file != null){
            CreateCSVFile createCSVFile = new CreateCSVFile();
            createCSVFile.createCSVVideoInfos(file);
        }
    }

    public void showVideoInfosLayout(IVideoInfo iVideoInfo) throws IOException{
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/videoInfos.fxml"));
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            Scene scene = new Scene(page);

            dialogStage.setTitle("Video");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);

            VideoInfoController videoInfoController = loader.getController();
            videoInfoController.setMainApp(this);
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
