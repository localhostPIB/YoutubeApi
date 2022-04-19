package util.converter;

import javafx.beans.property.SimpleStringProperty;
import model.classes.*;
import model.classes.fx.VideoInfoFx;
import model.interfaces.IVideoInfo;
import model.interfaces.fx.IVideoInfoFx;
import util.gui.i18n.I18nComponentsUtil;

import java.math.BigInteger;
import java.util.*;

public class VideoInfoConverter {

    public static IVideoInfoFx convertVideoInfoToVideoInfoFx(IVideoInfo iVideoInfo){
            IVideoInfoFx iVideoInfoFx = new VideoInfoFx();
            iVideoInfoFx.setVideoId(new SimpleStringProperty(iVideoInfo.getVideoId()));
            iVideoInfoFx.setVideoDescription(new SimpleStringProperty(iVideoInfo.getVideoDescription()));
            iVideoInfoFx.setChannelTitle(new SimpleStringProperty(iVideoInfo.getChannelTitle()));
            iVideoInfoFx.setFavorite(new SimpleStringProperty(iVideoInfo.getFavorite().toString()));
            if (iVideoInfo.getLikes() != null) {
                iVideoInfoFx.setLikes(new SimpleStringProperty(iVideoInfo.getLikes().toString()));
            } else {
                iVideoInfoFx.setLikes(new SimpleStringProperty(I18nComponentsUtil.getLabelNa()));
            }
            iVideoInfoFx.setTimestamp(new SimpleStringProperty(iVideoInfo.getTimestamp()));
            iVideoInfoFx.setTitle(new SimpleStringProperty(iVideoInfo.getTitle()));
            iVideoInfoFx.setCommentCount(new SimpleStringProperty(iVideoInfo.getCommentCount().toString()));
            iVideoInfoFx.setViewCount(new SimpleStringProperty(iVideoInfo.getViewCount().toString()));

        return iVideoInfoFx;
    }

    public static List<IVideoInfoFx> convertVideoInfoToVideoInfoFx(List<IVideoInfo> iVideoInfoList){
        List<IVideoInfoFx> videoInfoFxList = new ArrayList<>();

        for(IVideoInfo iVideoInfo :  iVideoInfoList){
           IVideoInfoFx iVideoInfoFx = convertVideoInfoToVideoInfoFx(iVideoInfo);

            videoInfoFxList.add(iVideoInfoFx);
        }

        return videoInfoFxList;
    }

    public static IVideoInfo convertVideoInfoFXtoVideoInfo(IVideoInfoFx iVideoInfoFx){
        IVideoInfo iVideoInfo = new VideoInfo();

        iVideoInfo.setVideoId(iVideoInfoFx.getVideoId().get());
        iVideoInfo.setChannelTitle(iVideoInfoFx.getChannelTitle().get());
        iVideoInfo.setTitle(iVideoInfoFx.getTitle().get());
        iVideoInfo.setTimestamp(iVideoInfoFx.getTimestamp().get());
        iVideoInfo.setVideoDescription(iVideoInfoFx.getVideoDescription().get());
        iVideoInfo.setViewCount(new BigInteger(String.valueOf(iVideoInfoFx.getViewCount().get())));
        iVideoInfo.setFavorite(new BigInteger(String.valueOf(iVideoInfoFx.getFavorite().get())));
        try {
            iVideoInfo.setLikes(new BigInteger(String.valueOf(iVideoInfoFx.getLikes().get())));
        } catch (NumberFormatException nfe) {
            iVideoInfo.setLikes(BigInteger.ZERO);
        }
        iVideoInfo.setCommentCount(new BigInteger(String.valueOf(iVideoInfoFx.getCommentCount().get())));

        return iVideoInfo;
    }
}
