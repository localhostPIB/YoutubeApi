package util;

import model.classes.*;
import model.interfaces.*;

import java.math.BigInteger;
import java.text.*;
import java.util.*;

public class StaticModelFactory {

    public static String getActualDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String strDate = dateFormat.format(date);

        return strDate;
    }

    public static IVideoInfo getVideoInfoObject(String videoId, String timestamp, BigInteger viewCount, BigInteger likes,
                                                BigInteger commentCount, BigInteger favorite){
        IVideoInfo iVideoInfo = new VideoInfo(videoId, timestamp, viewCount, likes,commentCount, favorite);

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

    public static IReply getReplyObject(String comment, IYoutubeUser iYoutubeUser, long likes, String publishAt){
        IReply iReply = new Reply(comment,iYoutubeUser, likes, publishAt);

        return iReply;
    }
}
