package model.interfaces.fx;

import javafx.beans.property.*;

public interface IReplyFx {

    LongProperty getLikes();

    StringProperty getPublishAt();

    StringProperty getComment();

    IYoutubeUserFx getIYoutubeUserFx();
    
    void setIYoutubeUserFx(IYoutubeUserFx iYoutubeUserFx);

    void setLikes(LongProperty likes);

    void setPublishAt(StringProperty publishAt);

    void setComment(StringProperty comment);
}
