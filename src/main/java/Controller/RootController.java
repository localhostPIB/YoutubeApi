package Controller;

import dao.VideoDaoHibernateImp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.api.videoInformations.GetYTVideoInformations;
import util.*;

/**
 * mvn clean javafx:run
 */
public class RootController {

    @FXML
    private TextField clientSecretField;

    @FXML
    private TextField videoIdField;

    private boolean okClicked = false;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public RootController(){}

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleOk() throws Exception {
        if (isInputValid()) {
            String clientSecret = clientSecretField.getText();
            String videoId = videoIdField.getText();

            if (!FileUtils.isFileExists("res/config.properties")) {
                PropertyUtils.writeInPropertyFile(clientSecret);
            }

            GetYTVideoInformations getYTVideoInformations = new GetYTVideoInformations(new VideoDaoHibernateImp());
            getYTVideoInformations.getYTVideoStatistics(videoId);

            okClicked = true;
        }
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private boolean isInputValid() {
        return clientSecretField.getText() != null || clientSecretField.getText().trim().isEmpty() ||
                videoIdField.getText() != null || videoIdField.getText().trim().isEmpty();
    }

    public boolean isOkClicked() {
        return okClicked;
    }

}
