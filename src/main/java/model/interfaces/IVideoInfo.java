package model.interfaces;


import java.math.BigInteger;

public interface IVideoInfo {
    String getVideoId();

    void setVideoId(String VideoId);

    void setTimestamp(String timestamp);

    void setChannelTitle(String ChannelTitle);

    void setViewCount(BigInteger count);

    void setLikes(BigInteger count);

    void setThumbnailUrl(String thumbnailUrl);

    void setVideoDescription(String videoDescription);

    void setCommentCount(BigInteger count);

    void setFavorite(BigInteger count);

    void setTitle(String title);

    String getTitle();

    String getThumbnailUrl();

    String getChannelTitle();

    String getTimestamp();

    String getVideoDescription();

    BigInteger getFavorite();

    BigInteger getViewCount();

    BigInteger getLikes();

    BigInteger getCommentCount();

    void addYoutubeUser(IYoutubeUser iYoutubeUser);
}
