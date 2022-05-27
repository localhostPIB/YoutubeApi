package util.gui.i18n;

import java.util.ResourceBundle;

public class I18nMessagesUtil {

    private static final String ERROR_WITHOUT_CLIENTID ="message-error-empty-clientid";
    private static final String ERROR_WITHOUT_VIDEOID  ="message-error-empty-videoid";
    private static final String ERROR_INVALID_VIDEOID  ="message-error-Incorrect-videoid";

    private static final String MESSAGE_LOADING        ="message-loading";
    private static final String ERROR_APP_CANT_START   ="message-error-cant-start";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getMessagesResourceBundle();
    }


    public static String getErrorWithoutClientId(){
        return resourceBundle.getString(ERROR_WITHOUT_CLIENTID);
    }

    public static String getErrorAppCantStart(){
        return resourceBundle.getString(ERROR_APP_CANT_START);
    }

    public static String getErrorWithoutVideoId(){
        return resourceBundle.getString(ERROR_WITHOUT_VIDEOID);
    }

    public static String getErrorInvalidVideoId(){
        return resourceBundle.getString(ERROR_INVALID_VIDEOID);
    }

    public static String getMessageLoading(){
        return resourceBundle.getString(MESSAGE_LOADING);
    }
}
