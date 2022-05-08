package controller.classes;

import controller.interfaces.IMainApp;
import dao.classes.CommentaryDaoHibernateImp;
import dao.classes.ReplyDaoHibernateImp;
import dao.classes.YTUserDaoHibernateImp;
import dao.interfaces.ICommentaryDaoHibernate;
import javafx.collections.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import model.classes.fx.VideoInfoFx;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.IVideoInfoFx;
import service.classes.CommentService;
import service.classes.VideoInfoService;
import service.classes.api.videoInformations.classes.GetYTCommentaries;
import service.classes.api.videoInformations.interfaces.IGetYTCommentaries;
import service.inferfaces.ICommentService;
import service.inferfaces.IVideoInfoService;
import util.converter.VideoInfoConverter;
import util.*;
import util.gui.FXUtils;
import util.gui.i18n.*;

import java.io.*;

/**
 * mvn clean javafx:run
 * --module-path "C:\bla\lib" --add-modules javafx.controls,javafx.web,javafx.fxml
 */
public class RootController {
    private IMainApp iMainApp;

    private final ObservableList<IVideoInfoFx> iVideoInfoData = FXCollections.observableArrayList();

    @FXML
    private TextField clientSecretField;

    @FXML
    private Button buttonStart;

    @FXML
    private Button buttonExit;

    @FXML
    private Button buttonCSV;

    @FXML
    private Button buttonHTML;

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
        this.iMainApp = mainApp;

        iVideoInfoData.addAll(this.iMainApp.getiVideoInfoFxList());
        videoInfoTable.setItems(iVideoInfoData);
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
    }

    @FXML
    private void handleRowSelect() throws IOException {
        ContextMenu contextMenu = new ContextMenu();
        IVideoInfoFx iVideoInfoFx = videoInfoTable.getSelectionModel().getSelectedItem();
        videoInfoTable.setOnMouseClicked(click -> {
            if (iVideoInfoFx != null) {
                if (click.getClickCount() == 2) {
                    openVideoInfo(iVideoInfoFx);
                } else if (click.getButton() == MouseButton.SECONDARY) {
                    MenuItem menuItemOpen = new MenuItem(I18nComponentsUtil.getLabelOpen());
                    MenuItem menuItemDelete = new MenuItem(I18nComponentsUtil.getLabelDelete());
                    contextMenu.getItems().add(menuItemOpen);
                    contextMenu.getItems().add(menuItemDelete);

                        menuItemOpen.setOnAction(e -> {
                            openVideoInfo(iVideoInfoFx);
                        });
                        menuItemDelete.setOnAction(e -> {
                            try {
                                deleteVideoInfo(iVideoInfoFx);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                        videoInfoTable.setContextMenu(contextMenu);
                    }
                }
        });

        videoInfoTable.setOnKeyPressed(key -> {
            if (iVideoInfoFx != null) {
                if (key.getCode() == KeyCode.ENTER) {
                    openVideoInfo(iVideoInfoFx);
                } else if (key.getCode() == KeyCode.DELETE) {
                    try {
                        deleteVideoInfo(iVideoInfoFx);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void openVideoInfo(IVideoInfoFx iVideoInfoFx) {
        IVideoInfo iVideoInfo = VideoInfoConverter.convertVideoInfoFXtoVideoInfo(iVideoInfoFx);
        try {
            iMainApp.showVideoInfosLayout(iVideoInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteVideoInfo(IVideoInfoFx iVideoInfoFx) throws Exception {
        this.iVideoInfoService.deleteVideoInfoById(iVideoInfoFx.getId().get());
        this.iVideoInfoData.remove(iVideoInfoFx);
    }

    @FXML
    private void handleCSV() throws Exception {
        iMainApp.showSaveFiles(FileEnum.CSV, I18nComponentsUtil.getLabelCsv(), I18nComponentsUtil.getLABELCSVFile(), "");
    }

    @FXML
    private void handleHTML() throws Exception {
        iMainApp.showSaveFiles(FileEnum.HTML, I18nComponentsUtil.getLABELHTML(), I18nComponentsUtil.getLABELHTMLFile(), "Video Infos");
    }

    @FXML
    private void handlePDF() throws Exception {
        iMainApp.showSaveFiles(FileEnum.PDF, I18nComponentsUtil.getLabelPDF(), I18nComponentsUtil.getLABELPDFFile(), "Video Infos");
    }

    @FXML
    private void handleStart() throws Exception {
        if (FXUtils.isInputValid(clientSecretField)) {
            if (FXUtils.isInputValid(videoIdField)) {
                String clientSecret = clientSecretField.getText();
                String videoId = videoIdField.getText();
                disableButtons();

                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        try {
                            PropertyUtils.writeInPropertyFile(clientSecret);
                            iVideoInfoService.initClientId();
                            IVideoInfo iVideoInfo = iVideoInfoService.callVideoInformations(videoId);
                            iVideoInfoService.getVideoInformations(iVideoInfo);
                            IVideoInfoFx iVideoInfoFx = VideoInfoConverter.convertVideoInfoToVideoInfoFx(iVideoInfo);
                            iVideoInfoData.add(iVideoInfoFx);
                            videoInfoTable.setItems(iVideoInfoData);

                            IGetYTCommentaries getYTCommentaries = new GetYTCommentaries(new YTUserDaoHibernateImp(),
                                    new CommentaryDaoHibernateImp(), new ReplyDaoHibernateImp(), iVideoInfo);
                            ICommentService iCommentService = new CommentService();
                            iCommentService.saveAllYTVideoMessagesByVideoId(videoId, getYTCommentaries);
                        } catch (Exception e) {
                            throw new Exception(e);
                        } finally {
                            enableButtons();
                        }
                        return null;
                    }
                };

                task.setOnSucceeded(e -> {
                    hideLoadScreen();
                });

                new Thread(task).start();
                loadScreen();
            } else {
                FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorWithoutVideoid(), ButtonType.OK);
            }

        } else {
            FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorWithoutClientid(), ButtonType.OK);
        }
    }

    private void loadScreen() throws IOException {
       iMainApp.showLoadScreen();
    }

    public void hideLoadScreen(){
        iMainApp.hideLoadScreen();
    }

    private void disableButtons() {
        buttonStart.setDisable(true);
        buttonHTML.setDisable(true);
        buttonCSV.setDisable(true);
        buttonExit.setDisable(true);
    }

    private void enableButtons() {
        buttonStart.setDisable(false);
        buttonHTML.setDisable(false);
        buttonCSV.setDisable(false);
        buttonExit.setDisable(false);
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
