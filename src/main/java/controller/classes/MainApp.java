package controller.classes;

import controller.interfaces.IMainApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.IVideoInfoFx;
import service.classes.VideoInfoService;
import service.inferfaces.IVideoInfoService;
import util.*;
import util.converter.VideoInfoConverter;
import util.gui.i18n.I18nUtil;

import java.io.*;
import java.util.*;

public class MainApp extends Application implements IMainApp {
    private AnchorPane rootLayout;

    private List<IVideoInfoFx> iVideoInfoFxList;

    private IVideoInfoService iVideoInfoService;

    private Stage primaryStage;

    private Stage loadStage;

    public MainApp() {

    }

    @Override
    public void init() throws Exception {
        try {
            FileUtils.createDirectory("res");
            this.iVideoInfoService = new VideoInfoService();
            this.iVideoInfoFxList = initVideoInfoList();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            this.primaryStage = stage;
            this.primaryStage.setTitle("Youtube Comment Picker Alpha");
            this.primaryStage.getIcons().add(new Image("images/youtube.png"));
            this.primaryStage.setResizable(false);

            initRootLayout();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        } catch (IOException ioException) {
            throw new IOException(ioException);
        }
    }

    public void showSaveFiles(final FileEnum fileEnum, final String i18n0, final String i18n1, final String docName) throws Exception {
        try {
            final FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle(i18n0);
            fileChooser.getExtensionFilters()
                    .add(new FileChooser.ExtensionFilter(i18n1, "*." + fileEnum.toString().toLowerCase()));
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                if (fileEnum.equals(FileEnum.CSV)) {
                    iVideoInfoService.createVideoInfosAsCSV(file);
                } else if (fileEnum.equals(FileEnum.HTML)) {
                    iVideoInfoService.createVideoInfosAsHTML(file, docName);
                } else if (fileEnum.equals(FileEnum.PDF)) {
                    iVideoInfoService.createVideoInfosAsPDF(file, docName);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void showVideoInfosLayout(IVideoInfo iVideoInfo) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getComponentsResourceBundle();
            loader.setLocation(getClass().getResource("/view/videoInfos.fxml"));
            loader.setResources(bundle);
            Pane page = loader.load();
            Stage dialogStage = new Stage();
            Scene scene = new Scene(page);

            dialogStage.setTitle(iVideoInfo.getTitle());
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(scene);
            dialogStage.getIcons().add(new Image("images/videocassette.png"));
            VideoInfoController videoInfoController = loader.getController();
            videoInfoController.setVideoInfo(iVideoInfo);
            videoInfoController.setMainApp(this);
            videoInfoController.setDialogStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public void showLoadScreen() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(getClass().getResource("/view/loadScreen.fxml"));
            loader.setResources(bundle);
            this.loadStage = new Stage();

            Pane page = loader.load();
            Scene scene = new Scene(page);

            this.loadStage.initStyle(StageStyle.TRANSPARENT);
            this.loadStage.setAlwaysOnTop(true);
            this.loadStage.initOwner(primaryStage);
            this.loadStage.setScene(scene);

            this.loadStage.show();
        } catch (IOException ioException) {
           throw new IOException(ioException);
        }
    }

    public void hideLoadScreen() {

        this.loadStage.close();
    }

    public List<IVideoInfoFx> initVideoInfoList() throws Exception {
        List<IVideoInfo> iVideoInfoList = iVideoInfoService.getAllVideoInfos();

        return VideoInfoConverter.
                convertVideoInfoToVideoInfoFx(iVideoInfoList);
    }

    public List<IVideoInfoFx> getiVideoInfoFxList() {
        return iVideoInfoFxList;
    }
}
