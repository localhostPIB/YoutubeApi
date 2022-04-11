package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.classes.VideoInfoFx;
import model.interfaces.IVideoInfo;
import service.VideoInfoService;
import util.*;
import util.converter.VideoInfoConverter;
import util.gui.FXUtils;

import java.io.*;
import java.util.List;

/**
 * mvn clean javafx:run
 */
public class RootController {
    private MainApp mainApp;

    private final ObservableList<VideoInfoFx> iVideoInfoData = FXCollections.observableArrayList();

    private IVideoInfo iVideoInfo;

    @FXML
    private TextField clientSecretField;

    @FXML
    private TextField videoIdField;

    @FXML
    private TableView<VideoInfoFx> videoInfoTable;

    @FXML
    private TableColumn<VideoInfoFx, String> videoIdColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> timeColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> titleColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> nameColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> descritpionColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> countColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> favoritColumn;

    @FXML
    private TableColumn<VideoInfoFx, String> commentCountColumn;

    private final VideoInfoService videoInfoService;



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public RootController() {
        videoInfoService = new VideoInfoService();
    }

    @FXML
    private void initialize() throws FileNotFoundException {
        initColumn();
        videoInfoService.initClientId();
        if (videoInfoService.getClientId() != null) {
            clientSecretField.setText(videoInfoService.getClientId());
        }

        List<VideoInfoFx> videoInfoFxList = VideoInfoConverter.
                convertVideoInfoToVideoInfoFx(videoInfoService.getAllVideoInfos());

        iVideoInfoData.addAll(videoInfoFxList);
        videoInfoTable.setItems(iVideoInfoData);
    }

    @FXML
    private void handleRowSelect() throws IOException {
        VideoInfoFx videoInfoFx = videoInfoTable.getSelectionModel().getSelectedItem();
        IVideoInfo iVideoInfo = VideoInfoConverter.convertVideoInfoFXtoVideoInfo(videoInfoFx);
        mainApp.showVideoInfosLayout(iVideoInfo);
    }

    @FXML
    private void handleStart() throws Exception {
        if (FXUtils.isInputValid(clientSecretField)) {
            if (FXUtils.isInputValid(videoIdField)) {
                String clientSecret = clientSecretField.getText();
                String videoId = videoIdField.getText();

                PropertyUtils.writeInPropertyFile(clientSecret);
                videoInfoService.initClientId();
                videoInfoService.getVideoInformations(videoId);
                videoInfoTable.getItems().clear();
                List<VideoInfoFx> videoInfoFxList = VideoInfoConverter.
                        convertVideoInfoToVideoInfoFx(videoInfoService.getAllVideoInfos());
                iVideoInfoData.addAll(videoInfoFxList);
                videoInfoTable.setItems(iVideoInfoData);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte geben Sie die VideoId ein wie z.B: FVFGFY5YmBI", ButtonType.OK);
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Bitte geben Sie ClientSecret Informationen ein", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private void initColumn() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getChannelTitle());
        favoritColumn.setCellValueFactory(cellData -> cellData.getValue().getFavorite());
        descritpionColumn.setCellValueFactory(cellData -> cellData.getValue().getVideoDescription());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().getViewCount());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimestamp());
        videoIdColumn.setCellValueFactory(cellData -> cellData.getValue().getVideoId());
        commentCountColumn.setCellValueFactory(cellData -> cellData.getValue().getCommentCount());
    }

}
