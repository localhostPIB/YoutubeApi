package util.gui.i18n;

import java.util.ResourceBundle;

public class I18nMessagesUtil {

    private static final String ERROR_WITHOUT_CLIENTID ="message-error-empty-clientid";
    private static final String ERROR_WITHOUT_VIDEOID  ="message-error-empty-videoid";
    private static final String MESSAGE_LOADING        ="message-loading";

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = I18nUtil.getMessagesResourceBundle();
    }


    public static String getErrorWithoutClientid(){
        return resourceBundle.getString(ERROR_WITHOUT_CLIENTID);
    }

    public static String getErrorWithoutVideoid(){
        return resourceBundle.getString(ERROR_WITHOUT_VIDEOID);
    }

    public static String getMessageLoading(){
        return resourceBundle.getString(MESSAGE_LOADING);
    }
}
