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

    public static IYoutubeUser getYoutubeUserObject(String userName, String channelUrl, String channelId,String imageUrl){
        IYoutubeUser iYoutubeUser = new YoutubeUser(channelId, userName, channelUrl, imageUrl );

        return iYoutubeUser;
    }

    public static ICommentary getCommentaryObject(){
        ICommentary iCommentary = new Commentary();

        return iCommentary;
    }

    public static IReply getReplyObject(String comment, IYoutubeUser iYoutubeUser){
        IReply iReply = new Reply(comment,iYoutubeUser);

        return iReply;
    }
}
