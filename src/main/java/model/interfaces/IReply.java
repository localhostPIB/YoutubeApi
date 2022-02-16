package model.interfaces;


public interface IReply {

    String getComment();

    void setComment(String comment);
    
    IYoutubeUser getIYoutubeUser();

    void setLikes(long count);

    long getLikes();

    void setIYoutubeUser(IYoutubeUser iYoutubeUser);

    String getPublishAt();

    void setPublishAt(String time);

}
