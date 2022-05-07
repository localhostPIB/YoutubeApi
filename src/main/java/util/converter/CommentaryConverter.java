package util.converter;

import javafx.beans.property.*;
import model.classes.fx.CommentaryFx;
import model.interfaces.ICommentary;
import model.interfaces.fx.ICommentaryFx;

public class CommentaryConverter {

    public static ICommentaryFx convertCommentaryToCommentaryFx(final ICommentary iCommentary){
        ICommentaryFx iCommentaryFx = new CommentaryFx();

        iCommentaryFx.setComment(new SimpleStringProperty(iCommentary.getComment()));
        iCommentaryFx.setLikes(new SimpleLongProperty(iCommentary.getLikes()));
        iCommentaryFx.setPublishAt(new SimpleStringProperty(iCommentary.getPublishAt()));

        return iCommentaryFx;
    }
}
