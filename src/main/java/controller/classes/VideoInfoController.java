package controller.classes;

import controller.interfaces.IMainApp;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.interfaces.IVideoInfo;
import util.constants.URLConstantUtils;

import java.awt.*;
import java.io.IOException;
import java.net.*;

public class VideoInfoController {

    private IVideoInfo iVideoInfo;

    private Stage dialogStage;

    private IMainApp iMainApp;

    private final String URL = URLConstantUtils.YOUTUBEURL;

    @FXML
    private WebView idWebView;

    public VideoInfoController() {

    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handlePlay() {
        String url = URL + URLConstantUtils.YOUTUBEEMBED + this.iVideoInfo.getVideoId();
        this.idWebView.getEngine().load(url);
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
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

}
