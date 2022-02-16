package model.interfaces;

import com.google.api.client.util.DateTime;

public interface ICommentary {
    String getComment();

    void setComment(String comment);

    IYoutubeUser getIYoutubeUser();

    void setIYoutubeUser(IYoutubeUser youtubeUser);

    void setLikes(long count);

    long getLikes();

    String getPublishAt();

    void setPublishAt(String time);

    void addIReply(IReply iReply);
}
