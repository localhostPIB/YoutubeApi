package controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.interfaces.IVideoInfo;

public class VideoInfoController {

    private IVideoInfo iVideoInfo;

    private Stage dialogStage;

    @FXML
    private WebView webView;

    @FXML
    private void initialize(){

    }

    @FXML
    private void handlePlay(){
        String url = "https://www.youtube.com/watch?v="+iVideoInfo.getVideoId();
        this.webView.getEngine().load(url);
    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    public void setVideoInfo(IVideoInfo iVideoInfo){
        this.iVideoInfo = iVideoInfo;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    }
