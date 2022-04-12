package util.gui;


import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import model.interfaces.fx.IVideoInfoFx;
import service.inferfaces.IVideoInfoService;
import util.converter.VideoInfoConverter;

import java.util.List;


public class FXUtils {

    public static void handleFXList(TableView<IVideoInfoFx> videoInfoTable, IVideoInfoService iVideoInfoService,
                                    ObservableList<IVideoInfoFx> iVideoInfoData){
        videoInfoTable.getItems().clear();
        List<IVideoInfoFx> iVideoInfoFxList = VideoInfoConverter.
                convertVideoInfoToVideoInfoFx(iVideoInfoService.getAllVideoInfos());
        iVideoInfoData.addAll(iVideoInfoFxList);
        videoInfoTable.setItems(iVideoInfoData);

    }

    public static boolean isInputValid(TextField textfield) {
        return textfield.getText() != null && !(textfield.getText().trim().isEmpty());
    }

    public static void showAlert(Alert.AlertType alertType, String message, ButtonType buttonType){
        Alert alert = new Alert(alertType, message, buttonType);
        alert.showAndWait();
    }
}
