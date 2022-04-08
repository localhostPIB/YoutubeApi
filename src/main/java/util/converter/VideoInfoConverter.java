package util.converter;

import javafx.beans.property.SimpleStringProperty;
import model.classes.*;
import model.interfaces.IVideoInfo;

import java.math.BigInteger;
import java.util.*;

public class VideoInfoConverter {

    public static List<VideoInfoFx> convertVideoInfotoVideoInfoFx(List<IVideoInfo> iVideoInfoList){
        List<VideoInfoFx> videoInfoFxList = new ArrayList<>();

        for(IVideoInfo iVideoInfo :  iVideoInfoList){
            VideoInfoFx videoInfoFx = new VideoInfoFx();
            videoInfoFx.setVideoId(new SimpleStringProperty(iVideoInfo.getVideoId()));
            videoInfoFx.setVideoDescription(new SimpleStringProperty(iVideoInfo.getVideoDescription()));
            videoInfoFx.setChannelTitle(new SimpleStringProperty(iVideoInfo.getChannelTitle()));
            videoInfoFx.setFavorite(new SimpleStringProperty(iVideoInfo.getFavorite().toString()));
            videoInfoFx.setLikes(new SimpleStringProperty(iVideoInfo.getLikes().toString()));
            videoInfoFx.setTimestamp(new SimpleStringProperty(iVideoInfo.getTimestamp()));
            videoInfoFx.setTitle(new SimpleStringProperty(iVideoInfo.getTitle()));
            videoInfoFx.setCommentCount(new SimpleStringProperty(iVideoInfo.getCommentCount().toString()));
            videoInfoFx.setViewCount(new SimpleStringProperty(iVideoInfo.getViewCount().toString()));

            videoInfoFxList.add(videoInfoFx);
        }

        return videoInfoFxList;
    }

    public static IVideoInfo convertVideoInfoFXtoVideoInfo(VideoInfoFx videoInfoFx){
        IVideoInfo iVideoInfo = new VideoInfo();

        iVideoInfo.setVideoId(videoInfoFx.getVideoId().get());
        iVideoInfo.setChannelTitle(videoInfoFx.getChannelTitle().get());
        iVideoInfo.setTitle(videoInfoFx.getTitle().get());
        iVideoInfo.setTimestamp(videoInfoFx.getTimestamp().get());
        iVideoInfo.setVideoDescription(videoInfoFx.getVideoDescription().get());
        iVideoInfo.setViewCount(new BigInteger(String.valueOf(videoInfoFx.getViewCount().get())));
        iVideoInfo.setFavorite(new BigInteger(String.valueOf(videoInfoFx.getFavorite().get())));
        iVideoInfo.setLikes(new BigInteger(String.valueOf(videoInfoFx.getLikes().get())));
        iVideoInfo.setCommentCount(new BigInteger(String.valueOf(videoInfoFx.getCommentCount().get())));

        return iVideoInfo;
    }
}
