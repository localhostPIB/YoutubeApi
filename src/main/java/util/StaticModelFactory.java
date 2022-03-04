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
        String stringDate = dateFormat.format(date);

        return stringDate;
    }

    public static IVideoInfo getVideoInfoObject(String videoId, String timestamp, BigInteger viewCount, BigInteger likes,
                                                BigInteger commentCount, BigInteger favorite, String title,
                                                String channelTitle, String videoDescription){

        IVideoInfo iVideoInfo = new VideoInfo(videoId, timestamp, viewCount, likes,commentCount, favorite,
                                              title, channelTitle, videoDescription);

        return iVideoInfo;
    }

    public static IYoutubeUser getYoutubeUserObject(String userName, String channelUrl, String channelId,
                                                    String imageUrl){
        IYoutubeUser iYoutubeUser = new YoutubeUser(channelId, userName, channelUrl, imageUrl);

        return iYoutubeUser;
    }

    public static ICommentary getCommentaryObject(long likes, String publishAt, String comment,
                                                  IYoutubeUser iYoutubeUser, IVideoInfo iVideoInfo){
        ICommentary iCommentary = new Commentary(likes, publishAt, comment, iYoutubeUser, iVideoInfo);

        return iCommentary;
    }

    public static IReply getReplyObject(String comment, IYoutubeUser iYoutubeUser, long likes, String publishAt){
        IReply iReply = new Reply(comment,iYoutubeUser, likes, publishAt);

        return iReply;
    }
}
