package model.interfaces.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface IVideoInfoFx {
    void setVideoId(StringProperty videoId);

    void setId(IntegerProperty id);

    void setTimestamp(StringProperty timestamp);

    void setTitle(StringProperty title);

    void setChannelTitle(StringProperty channelTitle);

    void setViewCount(StringProperty viewCount);

    void setLikes(StringProperty likes);

    void setVideoDescription(StringProperty videoDescription);

    void setCommentCount(StringProperty commentCount);

    void setFavorite(StringProperty favorite);

    StringProperty getVideoId();

    IntegerProperty getId();

    StringProperty getTimestamp();

    StringProperty getTitle();

    StringProperty getChannelTitle();

    StringProperty getViewCount();

    StringProperty getLikes();

    StringProperty getVideoDescription();

    StringProperty getCommentCount();

    StringProperty getFavorite();
}
