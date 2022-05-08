package controller.classes;

import controller.interfaces.IMainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.ICommentaryFx;
import service.classes.CommentService;
import service.inferfaces.ICommentService;
import util.constants.URLConstantUtils;
import util.converter.CommentaryConverter;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.List;

import static util.converter.CommentaryConverter.convertCommentaryToCommentaryFx;

public class VideoInfoController {

    private IVideoInfo iVideoInfo;

    private Stage dialogStage;

    private IMainApp iMainApp;

    private final String URL = URLConstantUtils.YOUTUBEURL;

    private final ObservableList<ICommentaryFx> iCommentData = FXCollections.observableArrayList();

    @FXML
    private WebView idWebView;

    @FXML
    private TableColumn<ICommentaryFx, String> userColumn;

    @FXML
    private TableColumn<ICommentaryFx, String> commentColumn;

    @FXML
    private TableView<ICommentaryFx> videoInfo;

    public VideoInfoController() {
    }

    @FXML
    private void initialize() {
        initColumn();
    }

    @FXML
    private void handlePlay() throws Exception {
        ICommentService iCommentService = new CommentService();
        String url = URL + URLConstantUtils.YOUTUBEEMBED + this.iVideoInfo.getVideoId();
        this.idWebView.getEngine().load(url);
        List<ICommentaryFx> iCommentaryFxList = CommentaryConverter.convertCommentaryToCommentaryFx(iCommentService.getAllYTVideoMessagesByVideoId(this.iVideoInfo.getVideoId()));
        iCommentData.addAll(iCommentaryFxList);
        videoInfo.setItems(iCommentData);

    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    @FXML
    private void handleOpenLink() throws IOException, URISyntaxException {
        String url = URL + URLConstantUtils.YOUTUBEWATCH + this.iVideoInfo.getVideoId();
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } catch (URISyntaxException uriSyntaxException) {
           throw new URISyntaxException("",uriSyntaxException.getMessage());
        }
    }

    public void setVideoInfo(IVideoInfo iVideoInfo) {
        this.iVideoInfo = iVideoInfo;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.iMainApp = mainApp;
    }

    private void initColumn() {
        userColumn.setCellValueFactory(cellData -> cellData.getValue().getIYoutubeUserFx().getUserName());
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());
    }

}
