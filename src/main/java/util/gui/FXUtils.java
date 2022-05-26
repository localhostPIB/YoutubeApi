package util.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.*;

public class FXUtils {

    public static boolean isInputValid(TextField textfield) {
        return textfield.getText() != null && !(textfield.getText().trim().isEmpty());
    }

    public static void showAlert(Alert.AlertType alertType, String message, ButtonType buttonType){
        Alert alert = new Alert(alertType, message, buttonType);
        alert.showAndWait();
    }
}
