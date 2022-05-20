package controller.classes;

import controller.interfaces.IMainApp;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.WebView;
import model.interfaces.*;
import model.interfaces.fx.*;

public class ReplyController {

    private IVideoInfo iVideoInfo;

    private IReply iReply;

    private ICommentary iCommentary;

    private IMainApp iMainApp;

    @FXML
    private TableColumn<IReplyFx, String> userColumn;

    @FXML
    private TableColumn<IReplyFx, String> commentColumn;

    @FXML
    private TableView<IReplyFx> replyTable;

    @FXML
    private void initialize(){
        initColumn();
    }

    private void initColumn(){
        userColumn.setCellValueFactory(cellData -> cellData.getValue().getIYoutubeUserFx().getUserName());
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());
    }

    public void setMainApp(IMainApp iMainApp){
        this.iMainApp = iMainApp;
    }

    public void setReply(IReply iReply){
        this.iReply = iReply;
    }
}
