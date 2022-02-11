package model.interfaces;


import model.classes.Commentary;

import java.util.List;

public interface IReply {

    String getComment();

    void setComment(String comment);
    
    IYoutubeUser getIYoutubeUser();

    void setIYoutubeUser(IYoutubeUser iYoutubeUser);
}
