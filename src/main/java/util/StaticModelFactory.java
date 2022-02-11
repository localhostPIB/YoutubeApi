package util;

import model.classes.*;
import model.interfaces.*;
import java.text.*;
import java.util.*;

public class StaticModelFactory {

    public static String getActualDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);

        return strDate;
    }

    public static IVideoInfo getVideoInfoObject(){
        IVideoInfo iVideoInfo = new VideoInfo();

        return iVideoInfo;
    }

    public static IYoutubeUser getYoutubeUserObject(String userName, String channelUrl){
        IYoutubeUser iYoutubeUser = new YoutubeUser(userName, channelUrl);

        return iYoutubeUser;
    }

    public static ICommentary getCommentaryObject(){
        ICommentary iCommentary = new Commentary();

        return iCommentary;
    }
}
