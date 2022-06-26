package controller.classes;

import controller.interfaces.IMainApp;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.interfaces.*;
import model.interfaces.fx.ICommentaryFx;
import service.classes.CommentService;
import service.classes.ReplyService;
import service.interfaces.ICommentService;
import service.interfaces.IReplyService;
import util.constants.URLConstantUtils;
import util.converter.CommentaryConverter;

import java.awt.*;
import java.io.IOException;
import java.net.*;
import java.util.List;


public class VideoInfoController {

    private IVideoInfo iVideoInfo;

    private Stage dialogStage;

    private IMainApp iMainApp;

    private final String URL = URLConstantUtils.YOUTUBEURL;

    private final ObservableList<ICommentaryFx> iCommentData = FXCollections.observableArrayList();

    private final IReplyService iReplyService;

    @FXML
    private WebView idWebView;

    @FXML
    private TableColumn<ICommentaryFx, String> userColumn;

    @FXML
    private TableColumn<ICommentaryFx, String> publishedAtColumn;

    @FXML
    private TableColumn<ICommentaryFx, String> commentColumn;

    @FXML
    private TableView<ICommentaryFx> videoInfo;

    public VideoInfoController() {
        this.iReplyService = new ReplyService();
    }

    @FXML
    private void initialize() {
        initColumn();
    }

    @FXML
    private void handleOpenLink() throws IOException, URISyntaxException {
        final String url = URL + URLConstantUtils.YOUTUBEWATCH + this.iVideoInfo.getVideoId();
        try {
            Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (IOException ioException) {
            throw new IOException(ioException);
        } catch (URISyntaxException uriSyntaxException) {
            throw new URISyntaxException("", uriSyntaxException.getMessage());
        }
    }

    public void setVideoInfo(IVideoInfo iVideoInfo) {
        this.iVideoInfo = iVideoInfo;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp) throws Exception {
        try {
            this.iMainApp = mainApp;

            String url = URL + URLConstantUtils.YOUTUBEEMBED + this.iVideoInfo.getVideoId();
            this.idWebView.getEngine().load(url);

            iCommentData.addAll(bootstrapCommentList());
            videoInfo.setItems(iCommentData);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    private List<ICommentaryFx> bootstrapCommentList() throws Exception {
        ICommentService iCommentService = new CommentService();

        try {
            List<ICommentary> iCommentaryList = iCommentService.getAllYTVideoMessagesByVideoId(this.iVideoInfo.getVideoId());
            List<ICommentaryFx> iCommentaryFxList = CommentaryConverter.convertCommentaryToCommentaryFx(iCommentaryList);

            return iCommentaryFxList;
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    @FXML
    private void handleRowSelect() {
        ICommentaryFx iCommentaryFx = videoInfo.getSelectionModel().getSelectedItem();

        videoInfo.setOnMouseClicked(click -> {
            if (iCommentaryFx != null) {
                if (click.getClickCount() == 2) {
                    try {
                        openReplies(iCommentaryFx);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    private void openReplies(ICommentaryFx iCommentaryFx) throws Exception {
        try {
            List<IReply> iReplyList = iReplyService.getRepliesById(iCommentaryFx.getId().get());

            if (iReplyList.size() > 0) {
                iMainApp.showReplyLayout(iReplyList, iCommentaryFx.getComment().get());
            }
        } catch (IOException e) {
            throw new IOException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void initColumn() {
        userColumn.setCellValueFactory(cellData -> cellData.getValue().getIYoutubeUserFx().getUserName());
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());
        publishedAtColumn.setCellValueFactory(cellData -> cellData.getValue().getPublishAt());
    }
}
