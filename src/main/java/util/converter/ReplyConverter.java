package util.converter;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import model.classes.fx.ReplyFx;
import model.interfaces.IReply;
import model.interfaces.fx.IReplyFx;

import java.util.ArrayList;
import java.util.List;

public class ReplyConverter {

    private static IReplyFx convertReplytoReplyFx(IReply iReply){
        IReplyFx iReplyFx = new ReplyFx();

        iReplyFx.setComment(new SimpleStringProperty(iReply.getComment()));
        iReplyFx.setLikes(new SimpleLongProperty(iReply.getLikes()));
        iReplyFx.setPublishAt(new SimpleStringProperty(iReply.getPublishAt()));
        iReplyFx.setIYoutubeUserFx(YoutubeUserConverter.convertYoutubeUserToYoutubeUserFx(iReply.getIYoutubeUser()));

        return iReplyFx;
    }

    public static List<IReplyFx> convertReliesToReplyFx(List<IReply> iReplyList){
        List<IReplyFx> iReplyFxList = new ArrayList<>();

        for(IReply iReply : iReplyList){
            IReplyFx iReplyFx = convertReplytoReplyFx(iReply);
            iReplyFxList.add(iReplyFx);
        }

        return  iReplyFxList;
    }
}
