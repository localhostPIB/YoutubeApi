package model.classes.fx;

import javafx.beans.property.*;
import lombok.*;
import model.interfaces.fx.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyFx implements IReplyFx {

    private StringProperty comment;

    private LongProperty likes;

    private StringProperty publishAt;

    private IYoutubeUserFx iYoutubeUserFx;
}
