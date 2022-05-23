package model.classes.fx;

import javafx.beans.property.*;
import lombok.*;
import model.interfaces.fx.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentaryFx implements ICommentaryFx {
    private LongProperty likes;

    private IntegerProperty id;

    private StringProperty publishAt;

    private StringProperty comment;

    private ListProperty<IReplyFx> iReplyListFx = new SimpleListProperty<IReplyFx>();

    private IVideoInfoFx iVideoInfoFx;

    private IYoutubeUserFx iYoutubeUserFx;
}
