package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.IVideoInfoFx;
import service.classes.VideoInfoService;
import service.classes.csv.CreateCSVFile;
import service.classes.html.CreateHTMLFile;
import service.inferfaces.IVideoInfoService;
import util.*;
import util.converter.VideoInfoConverter;
import util.gui.i18n.I18nUtil;

import java.io.*;
import java.util.List;
import java.util.ResourceBundle;

public class MainApp extends Application {
    private AnchorPane rootLayout;

    private List<IVideoInfoFx> iVideoInfoFxList;

    private IVideoInfoService iVideoInfoService;

    private Stage primaryStage;

    public MainApp(){

    }

    @Override
    public void init() throws Exception {
        this.iVideoInfoService = new VideoInfoService();
        this.iVideoInfoFxList = initVideoInfoList();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FileUtils.createDirectory("res");
        this.primaryStage = stage;
        this.primaryStage.setTitle("Youtube Comment Picker Alpha");
        this.primaryStage.getIcons().add(new Image("images/youtube.png"));
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

    public void showSaveFiles(final FileEnum fileEnum, final String i18n0,final String i18n1, final String docName) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(i18n0);
        fileChooser.getExtensionFilters()
                   .add(new FileChooser.ExtensionFilter(i18n1, "*."+fileEnum.toString().toLowerCase()));
        File file = fileChooser.showSaveDialog(primaryStage);

        if(file != null){
            if(fileEnum.equals(FileEnum.CSV)) {
                CreateCSVFile createCSVFile = new CreateCSVFile();
                createCSVFile.createCSVVideoInfos(file);
            }else if(fileEnum.equals(FileEnum.HTML)){
                CreateHTMLFile createHTMLFile = new CreateHTMLFile();
                createHTMLFile.writeHTMLFile(file, docName);
            }
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

    public List<IVideoInfoFx> initVideoInfoList(){
        List<IVideoInfo> iVideoInfoList = iVideoInfoService.getAllVideoInfos();

        return VideoInfoConverter.
                convertVideoInfoToVideoInfoFx(iVideoInfoList);
    }

    public List<IVideoInfoFx> getiVideoInfoFxList() {
        return iVideoInfoFxList;
    }
}
