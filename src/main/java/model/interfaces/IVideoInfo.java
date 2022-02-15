package model.interfaces;


import java.math.BigInteger;

public interface IVideoInfo {
    String getVideoId();

    void setVideoId(String VideoId);

    void setTimestamp(String timestamp);

    void setViewCount(BigInteger count);

    void setLikes(BigInteger count);

    void setCommentCount(BigInteger count);

    void setFavorite(BigInteger count);

    BigInteger getFavorite();

    BigInteger getViewCount();

    BigInteger getLikes();

    BigInteger getCommentCount();
}
