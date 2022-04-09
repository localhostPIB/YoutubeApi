package util.gui;

import javafx.scene.control.TextField;

public class FXUtils {

    public static boolean isInputValid(TextField textfield) {
        return textfield.getText() != null && !(textfield.getText().trim().isEmpty());
    }

}
