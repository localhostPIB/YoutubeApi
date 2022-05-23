package model.interfaces.fx;

import javafx.beans.property.*;

public interface ICommentaryFx {
    LongProperty getLikes();

    IntegerProperty getId();

    void setId(IntegerProperty id);

    StringProperty getPublishAt();

    StringProperty getComment();

    IVideoInfoFx getIVideoInfoFx();

    IYoutubeUserFx getIYoutubeUserFx();

    ListProperty<IReplyFx> getIReplyListFx();

    void setIYoutubeUserFx(IYoutubeUserFx iYoutubeUserFx);

    void setIVideoInfoFx(IVideoInfoFx iVideoInfoFx);

    void setLikes(LongProperty likes);

    void setComment(StringProperty comment);

    void setPublishAt(StringProperty publishAt);

    void setIReplyListFx(ListProperty<IReplyFx> iReplyFxListProperty);
}
