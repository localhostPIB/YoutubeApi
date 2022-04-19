package util.gui.i18n;

import java.util.ResourceBundle;


public class I18nUtil {
    private static ResourceBundle resourceBundleComponents;
    private static ResourceBundle resourceBundleMessages;

    private static final String I18N_BASENAME_COMPONENTS = "i18n.components";
    private static final String I18N_BASENAME_MESSAGES   = "i18n.messages";

    static {
        resourceBundleComponents = ResourceBundle.getBundle(I18N_BASENAME_COMPONENTS);
        resourceBundleMessages = ResourceBundle.getBundle(I18N_BASENAME_MESSAGES);
    }

    public static ResourceBundle getComponentsResourceBundle() {
        return resourceBundleComponents;
    }

    public static ResourceBundle getMessagesResourceBundle(){
        return resourceBundleMessages;
    }
}
