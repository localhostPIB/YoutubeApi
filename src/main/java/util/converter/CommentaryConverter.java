package util.converter;

import javafx.beans.property.*;
import model.classes.fx.CommentaryFx;
import model.interfaces.ICommentary;
import model.interfaces.fx.ICommentaryFx;

import java.util.*;

public class CommentaryConverter {

    public static ICommentaryFx convertCommentaryToCommentaryFx(final ICommentary iCommentary){
        ICommentaryFx iCommentaryFx = new CommentaryFx();

        iCommentaryFx.setId(new SimpleIntegerProperty(iCommentary.getId()));
        iCommentaryFx.setComment(new SimpleStringProperty(iCommentary.getComment()));
        iCommentaryFx.setLikes(new SimpleLongProperty(iCommentary.getLikes()));
        iCommentaryFx.setPublishAt(new SimpleStringProperty(iCommentary.getPublishAt()));
        iCommentaryFx.setIYoutubeUserFx(YoutubeUserConverter.convertYoutubeUserToYoutubeUserFx(iCommentary.getIYoutubeUser()));

        return iCommentaryFx;
    }

    public static List<ICommentaryFx> convertCommentaryToCommentaryFx(final List<ICommentary> iCommentaryList){
        List<ICommentaryFx> iCommentaryFxList = new ArrayList<>();

        for (ICommentary iCommentary : iCommentaryList){
            ICommentaryFx iCommentaryFx = convertCommentaryToCommentaryFx(iCommentary);
            iCommentaryFxList.add(iCommentaryFx);
        }

        return iCommentaryFxList;
    }
}
