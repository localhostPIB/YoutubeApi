package controller.interfaces;

import javafx.stage.Stage;
import model.interfaces.IReply;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.IVideoInfoFx;
import util.FileEnum;

import java.io.IOException;
import java.util.List;

public interface IMainApp {
    void init() throws Exception;

    void start(Stage stage) throws Exception;

    void initRootLayout() throws IOException;

    void showSaveFiles(final FileEnum fileEnum, final String i18n0, final String i18n1, final String docName) throws Exception;

    void showVideoInfosLayout(IVideoInfo iVideoInfo) throws Exception;

    void showLoadScreen() throws IOException;

    void hideLoadScreen();

    List<IVideoInfoFx> initVideoInfoList() throws Exception;

    List<IVideoInfoFx> getiVideoInfoFxList();

    void showReplyLayout(List<IReply> iReply, String name) throws Exception;

}
