package model.interfaces;

import java.util.List;

public interface ICommentary {
    String getComment();

    void setComment(String comment);

    IYoutubeUser getIYoutubeUser();

    IVideoInfo getIVideoInfo();

    List<IReply> getIReplyList();

    void setIYoutubeUser(IYoutubeUser iYoutubeUser);

    void setLikes(long count);

    long getLikes();

    String getPublishAt();

    void setPublishAt(String publishAt);

    void addIReply(IReply iReply);

    void setIVideoInfo(IVideoInfo iVideoInfo);

}
