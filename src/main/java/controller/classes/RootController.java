package controller.classes;

import controller.interfaces.IMainApp;
import dao.classes.*;
import exceptions.*;
import javafx.collections.*;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import model.classes.fx.VideoInfoFx;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.IVideoInfoFx;
import service.classes.*;
import service.classes.api.videoInformations.classes.GetYTCommentaries;
import service.classes.api.videoInformations.interfaces.IGetYTCommentaries;
import service.interfaces.*;
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
    private Button buttonPDF;

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

    private final ICommentService iCommentService;

    private final IReplyService iReplyService;


    public void setMainApp(MainApp mainApp) {
        this.iMainApp = mainApp;

        if(this.iMainApp.getiVideoInfoFxList().size() == 0){
            buttonPDF.setDisable(true);
            buttonCSV.setDisable(true);
            buttonHTML.setDisable(true);
        }else {
            iVideoInfoData.addAll(this.iMainApp.getiVideoInfoFxList());
            videoInfoTable.setItems(iVideoInfoData);
        }
    }

    public RootController() {
        iVideoInfoService = new VideoInfoService();
        iCommentService   = new CommentService();
        iReplyService     = new ReplyService();
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
    private void handleRowSelect() {
        ContextMenu contextMenu = new ContextMenu();
        IVideoInfoFx iVideoInfoFx = videoInfoTable.getSelectionModel().getSelectedItem();
        videoInfoTable.setOnMouseClicked(click -> {
            if (iVideoInfoFx != null) {
                if (click.getClickCount() == 2) {
                    try {
                        openVideoInfo(iVideoInfoFx);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (click.getButton() == MouseButton.SECONDARY) {
                    MenuItem menuItemOpen = new MenuItem(I18nComponentsUtil.getLabelOpen());
                    MenuItem menuItemDelete = new MenuItem(I18nComponentsUtil.getLabelDelete());
                    contextMenu.getItems().add(menuItemOpen);
                    contextMenu.getItems().add(menuItemDelete);

                        menuItemOpen.setOnAction(e -> {
                            try {
                                openVideoInfo(iVideoInfoFx);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
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
                    try {
                        openVideoInfo(iVideoInfoFx);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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

    private void openVideoInfo(IVideoInfoFx iVideoInfoFx) throws Exception {
        IVideoInfo iVideoInfo = VideoInfoConverter.convertVideoInfoFXtoVideoInfo(iVideoInfoFx);
        try {
            iMainApp.showVideoInfosLayout(iVideoInfo);
        } catch (IOException e) {
            throw new IOException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void deleteVideoInfo(IVideoInfoFx iVideoInfoFx) throws Exception {
        this.iReplyService.deleteRepliesById(iVideoInfoFx.getVideoId().get());
        this.iCommentService.deleteAllYTVideoMessagesByVideoId(iVideoInfoFx.getVideoId().get());
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
    private void handleStart() throws StringIsEmptyException, IOException {
        if (FXUtils.isInputValid(clientSecretField)) {
            if (FXUtils.isInputValid(videoIdField)) {
                String clientSecret = clientSecretField.getText();
                String videoId = videoIdField.getText();
                disableButtonsAndTable();

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
                            e.printStackTrace();
                            throw new Exception(e);
                        } finally {
                            enableButtonsAndTable();
                        }
                        return null;
                    }
                };

                task.setOnSucceeded(e -> hideLoadScreen());
                task.setOnFailed(e -> handleError());

                new Thread(task).start();
                loadScreen();
            } else {
                FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorWithoutVideoId(), ButtonType.OK);
                throw new StringIsEmptyException();
            }

        } else {
            FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorWithoutClientId(), ButtonType.OK);
            throw new StringIsEmptyException();
        }
    }

    private void handleError(){
        hideLoadScreen();
        FXUtils.showAlert(Alert.AlertType.ERROR, I18nMessagesUtil.getErrorInvalidVideoId(), ButtonType.OK);

        throw new InvalidVideoIdException();
    }

    private void loadScreen() throws IOException {
       iMainApp.showLoadScreen();
    }

    public void hideLoadScreen(){
        iMainApp.hideLoadScreen();
    }

    private void disableButtonsAndTable() {
        videoInfoTable.setDisable(true);
        buttonStart.setDisable(true);
        buttonHTML.setDisable(true);
        buttonCSV.setDisable(true);
        buttonExit.setDisable(true);
        buttonPDF.setDisable(true);
    }

    private void enableButtonsAndTable() {
        videoInfoTable.setDisable(false);
        buttonStart.setDisable(false);
        buttonHTML.setDisable(false);
        buttonCSV.setDisable(false);
        buttonExit.setDisable(false);
        buttonPDF.setDisable(false);
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
