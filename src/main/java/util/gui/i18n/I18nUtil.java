package util.gui.i18n;

import java.util.ResourceBundle;


public class I18nUtil {

    private static ResourceBundle resourceBundleComponents;

    private static final String I18N_BASENAME_COMPONENTS = "i18n.components";

    static {
        resourceBundleComponents = ResourceBundle.getBundle(I18N_BASENAME_COMPONENTS);
    }

    public static ResourceBundle getComponentsResourceBundle() {
        return resourceBundleComponents;
    }
}
