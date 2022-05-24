package controller.classes;

import controller.interfaces.IMainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.interfaces.*;
import model.interfaces.fx.*;
import util.converter.ReplyConverter;

import java.util.List;

public class ReplyController {

    private List<IReply> iReplyList;

    private final ObservableList<IReplyFx> iReplyData = FXCollections.observableArrayList();

    private IMainApp iMainApp;

    @FXML
    private TableColumn<IReplyFx, String> userColumn;

    @FXML
    private TableColumn<IReplyFx, String> commentColumn;

    @FXML
    private TableColumn<IReplyFx, String> publishedAtColumn;

    @FXML
    private TableColumn<IReplyFx, Long> likeColumn;

    @FXML
    private TableView<IReplyFx> replyTable;

    @FXML
    private void initialize(){
        initColumn();
    }

    private void initColumn(){
        userColumn.setCellValueFactory(cellData -> cellData.getValue().getIYoutubeUserFx().getUserName());
        likeColumn.setCellValueFactory(cellData -> cellData.getValue().getLikes().asObject());
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().getComment());
        publishedAtColumn.setCellValueFactory(cellData -> cellData.getValue().getPublishAt());
    }

    public void setMainApp(IMainApp iMainApp){

        List<IReplyFx> iReplyFxList = ReplyConverter.convertReliesToReplyFx(this.iReplyList);

        iReplyData.addAll(iReplyFxList);
        replyTable.setItems(iReplyData);

        this.iMainApp = iMainApp;
    }

    public void setReply(List<IReply> iReplyList){
        this.iReplyList = iReplyList;
    }
}
