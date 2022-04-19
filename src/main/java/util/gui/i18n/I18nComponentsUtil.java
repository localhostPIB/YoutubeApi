package util.gui.i18n;

import java.util.ResourceBundle;


public class I18nComponentsUtil {

    private static final String BUTTON_EXIT               ="button-exit";

    private static final String BUTTON_START              ="button-start";

    private static final String COLUMN_TIME               ="timeColumn";

    private static final String COLUMN_TITLE              ="titleColumn";

    private static final String COLUMN_CHANNELNAME        ="nameColumn";

    private static final String COLUMN_DESCRIPTION        ="descriptionColumn";

    private static final String COLUMN_COUNT              ="countColumn";

    private static final String COLUMN_FAVORITE           ="favoritColumn";

    private static final String COLUMN_LIKES              ="likeColumn";

    private static final String COLUMN_COMMENTCOUNT       ="commentCountColumn";

    private static final String COLUMN_VIDEOID            = "videoIdColumn";

    private static final String LABEL_CLIENTSECRET        ="labelYTCS";

    private static final String LABEL_VIDEOID             ="labelVideoId";

    private static final String LABEL_NA                  ="label-nA";

    private static ResourceBundle resourceBundle;


    static {
        resourceBundle = I18nUtil.getComponentsResourceBundle();
    }

    public static String getLabelNa(){
        return resourceBundle.getString(LABEL_NA);
    }

    public static String getButtonExitString() {
        return resourceBundle.getString(BUTTON_EXIT);
    }

    public static String getColumnLikes(){
        return resourceBundle.getString(COLUMN_LIKES);
    }

    public static String getButtonStartString() {
        return resourceBundle.getString(BUTTON_START);
    }

    public static String getColumnVideoid(){
        return resourceBundle.getString(COLUMN_VIDEOID);
    }

    public static String getColumnTime(){
        return resourceBundle.getString(COLUMN_TIME);
    }

    public static String getColumnChannelname(){
        return resourceBundle.getString(COLUMN_CHANNELNAME);
    }

    public static String getColumnDescription(){
        return resourceBundle.getString(COLUMN_DESCRIPTION);
    }

    public static String getColumnCount(){
        return resourceBundle.getString(COLUMN_COUNT);
    }

    public static String getColumnFavorite(){
        return resourceBundle.getString(COLUMN_FAVORITE);
    }

    public static String getColumnTitle(){
        return resourceBundle.getString(COLUMN_TITLE);
    }

    public static String getColumnCommentcount(){
        return resourceBundle.getString(COLUMN_COMMENTCOUNT);
    }

    public static String getLabelClientsecret(){
        return resourceBundle.getString(LABEL_CLIENTSECRET);
    }

    public static String getLabelVideoid(){
        return resourceBundle.getString(LABEL_VIDEOID);
    }

}
