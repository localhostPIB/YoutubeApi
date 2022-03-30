package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.classes.VideoInfoFx;
import model.interfaces.IVideoInfo;
import service.VideoInfoService;
import util.*;
import util.converter.VideoInfoConverter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * mvn clean javafx:run
 */
public class RootController {

    private boolean okClicked = false;

    private MainApp mainApp;

    private ObservableList<VideoInfoFx> iVideoInfoData = FXCollections.observableArrayList();

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

    public RootController() throws FileNotFoundException {
        videoInfoService = new VideoInfoService();
    }

    @FXML
    private void initialize() throws FileNotFoundException {
        initColumn();
        videoInfoService.initClientId();
        if(videoInfoService.getClientId() != null){
            clientSecretField.setText(videoInfoService.getClientId());
        }

        List<VideoInfoFx> videoInfoFxList = VideoInfoConverter.
                convertVideoInfotoVideoInfoFx(videoInfoService.getAllVideoInfos());

        iVideoInfoData.addAll(videoInfoFxList);
        videoInfoTable.setItems(iVideoInfoData);
    }

    @FXML
    private void handleRowSelect() throws IOException {
        VideoInfoFx videoInfoFx = videoInfoTable.getSelectionModel().getSelectedItem();
        IVideoInfo iVideoInfo   = VideoInfoConverter.convertVideoInfoFXtoVideoInfo(videoInfoFx);
        mainApp.showVideoInfosLayout(iVideoInfo);
    }

    @FXML
    private void handleStart() throws Exception {
        if (isInputValid()) {
            String clientSecret = clientSecretField.getText();
            String videoId = videoIdField.getText();

            PropertyUtils.writeInPropertyFile(clientSecret);
            videoInfoService.initClientId();
            videoInfoService.getVideoInformations(videoId);
            videoInfoTable.getItems().clear();
            List<VideoInfoFx> videoInfoFxList = VideoInfoConverter.
                    convertVideoInfotoVideoInfoFx(videoInfoService.getAllVideoInfos());

            iVideoInfoData.addAll(videoInfoFxList);
            videoInfoTable.setItems(iVideoInfoData);

            okClicked = true;
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private boolean isInputValid() {
        return clientSecretField.getText() != null || clientSecretField.getText().trim().isEmpty() ||
                videoIdField.getText() != null || videoIdField.getText().trim().isEmpty();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private void initColumn(){
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
