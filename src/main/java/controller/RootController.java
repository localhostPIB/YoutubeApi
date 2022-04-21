package controller;

import javafx.collections.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import model.classes.fx.VideoInfoFx;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.IVideoInfoFx;
import service.classes.VideoInfoService;
import service.inferfaces.IVideoInfoService;
import util.*;
import util.converter.VideoInfoConverter;
import util.gui.FXUtils;
import util.gui.i18n.I18nMessagesUtil;

import java.io.*;
import java.util.List;

/**
 * mvn clean javafx:run
 * --module-path "C:\bla\lib" --add-modules javafx.controls,javafx.web,javafx.fxml
 */
public class RootController {
    private MainApp mainApp;

    private final ObservableList<IVideoInfoFx> iVideoInfoData = FXCollections.observableArrayList();

    @FXML
    private TextField clientSecretField;

    @FXML
    private TextField videoIdField;

    @FXML
    private TableView<IVideoInfoFx> videoInfoTable;

    @FXML
    private TableColumn<VideoInfoFx, String> videoIdColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> timeColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> titleColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> nameColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> descriptionColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> countColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> likeColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> favoritColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> commentCountColumn;

    private final IVideoInfoService iVideoInfoService;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public RootController() {
        iVideoInfoService = new VideoInfoService();
    }

    @FXML
    private void initialize() throws FileNotFoundException {
        initColumn();
        iVideoInfoService.initClientId();
        if (iVideoInfoService.getClientId() != null) {
            clientSecretField.setText(iVideoInfoService.getClientId());
        }

        List<IVideoInfoFx> videoInfoFxList = VideoInfoConverter.
                convertVideoInfoToVideoInfoFx(iVideoInfoService.getAllVideoInfos());

        iVideoInfoData.addAll(videoInfoFxList);
        videoInfoTable.setItems(iVideoInfoData);
    }

    @FXML
    private void handleRowSelect() throws IOException {
        IVideoInfoFx iVideoInfoFx = videoInfoTable.getSelectionModel().getSelectedItem();
        IVideoInfo iVideoInfo = VideoInfoConverter.convertVideoInfoFXtoVideoInfo(iVideoInfoFx);
        mainApp.showVideoInfosLayout(iVideoInfo);
    }

    @FXML
    private void handleCSV() throws IOException {
        mainApp.showSaveFiles();
    }

    @FXML
    private void handleStart() throws Exception {
        if (FXUtils.isInputValid(clientSecretField)) {
            if (FXUtils.isInputValid(videoIdField)) {
                String clientSecret = clientSecretField.getText();
                String videoId = videoIdField.getText();

                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            PropertyUtils.writeInPropertyFile(clientSecret);
                            iVideoInfoService.initClientId();
                            IVideoInfo iVideoInfo = iVideoInfoService.callVideoInformations(videoId);
                            IVideoInfoFx iVideoInfoFx = VideoInfoConverter.convertVideoInfoToVideoInfoFx(iVideoInfo);
                            iVideoInfoService.getVideoInformations(iVideoInfo);
                            iVideoInfoData.add(iVideoInfoFx);
                            videoInfoTable.setItems(iVideoInfoData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                };
                Thread thread = new Thread(task);
                thread.start();
                //PropertyUtils.writeInPropertyFile(clientSecret);
                //iVideoInfoService.initClientId();
                //IVideoInfo iVideoInfo = iVideoInfoService.callVideoInformations(videoId);
                //IVideoInfoFx iVideoInfoFx = VideoInfoConverter.convertVideoInfoToVideoInfoFx(iVideoInfo);
                //iVideoInfoService.getVideoInformations(iVideoInfo);
                //iVideoInfoData.add(iVideoInfoFx);
                //videoInfoTable.setItems(iVideoInfoData);
            } else {
                FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorWithoutVideoid(), ButtonType.OK);
            }

        } else {
            FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorWithoutClientid(), ButtonType.OK);
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void initColumn() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getChannelTitle());
        likeColumn.setCellValueFactory(cellData -> cellData.getValue().getLikes());
        favoritColumn.setCellValueFactory(cellData -> cellData.getValue().getFavorite());
        descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().getVideoDescription());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().getViewCount());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimestamp());
        videoIdColumn.setCellValueFactory(cellData -> cellData.getValue().getVideoId());
        commentCountColumn.setCellValueFactory(cellData -> cellData.getValue().getCommentCount());
    }

}
