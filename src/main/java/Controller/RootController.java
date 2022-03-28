package Controller;

import dao.VideoDaoHibernateImp;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.classes.VideoInfoFx;
import service.api.videoInformations.GetYTVideoInformations;
import util.*;
import util.converter.VideoInfoConverter;

import java.util.List;

/**
 * mvn clean javafx:run
 */
public class RootController {

    private boolean okClicked = false;

    private MainApp mainApp;

    private ObservableList<VideoInfoFx> iVideoInfoData = FXCollections.observableArrayList();

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

    private VideoDaoHibernateImp videoDaoHibernateImp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public RootController(){
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getChannelTitle());
        favoritColumn.setCellValueFactory(cellData -> cellData.getValue().getFavorite());
        descritpionColumn.setCellValueFactory(cellData -> cellData.getValue().getVideoDescription());
        countColumn.setCellValueFactory(cellData -> cellData.getValue().getViewCount());
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimestamp());
        videoIdColumn.setCellValueFactory(cellData -> cellData.getValue().getVideoId());
        commentCountColumn.setCellValueFactory(cellData -> cellData.getValue().getCommentCount());

        this.videoDaoHibernateImp = new VideoDaoHibernateImp();
        List<VideoInfoFx> videoInfoFxList = VideoInfoConverter.convertVideoInfotoVideoInfoFx(videoDaoHibernateImp.findAllVideoInfos());
        iVideoInfoData.addAll(videoInfoFxList);
        videoInfoTable.setItems(iVideoInfoData);
    }

    @FXML
    private void handleOk() throws Exception {
        if (isInputValid()) {
            String clientSecret = clientSecretField.getText();
            String videoId = videoIdField.getText();

            if (!FileUtils.isFileExists("res/config.properties")) {
                PropertyUtils.writeInPropertyFile(clientSecret);
            }

            GetYTVideoInformations getYTVideoInformations = new GetYTVideoInformations(new VideoDaoHibernateImp());
            getYTVideoInformations.getYTVideoStatistics(videoId);

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

}
