package util.gui.i18n;

import java.util.ResourceBundle;


public class I18nComponentsUtil {

    private static final String BUTTON_EXIT               ="button-exit";

    private static final String BUTTON_START              ="button-start";

    private static final String BUTTON_CSV                ="button-csv";

    private static final String BUTTON_HTML               ="button-html";

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

    private static final String LABEL_CSV                 ="labelCSV";

    private static final String LABEL_CSVFile             ="labelCSVFile";

    private static final String LABEL_HTML                ="labelHTML";

    private static final String LABEL_HTMLFile            ="labelHTMLFile";

    private static final String LABEL_VIDEOID             ="labelVideoId";

    private static final String LABEL_NA                  ="label-nA";

    private static ResourceBundle resourceBundle;


    static {
        resourceBundle = I18nUtil.getComponentsResourceBundle();
    }

    public static String getLabelNa(){
        return resourceBundle.getString(LABEL_NA);
    }

    public static String getButtonCsv(){
        return resourceBundle.getString(BUTTON_CSV);
    }

    public static String getButtonHtml(){
        return resourceBundle.getString(BUTTON_HTML);
    }

    public static String getLabelCsv(){
        return resourceBundle.getString(LABEL_CSV);
    }

    public static String getLABELCSVFile(){
        return resourceBundle.getString(LABEL_CSVFile);
    }

    public static String getLABELHTMLFile(){
        return resourceBundle.getString(LABEL_HTMLFile);
    }

    public static String getLABELHTML(){
        return resourceBundle.getString(LABEL_HTML);
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
