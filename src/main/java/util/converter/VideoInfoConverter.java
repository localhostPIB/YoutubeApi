package util.converter;

import javafx.beans.property.SimpleStringProperty;
import model.classes.*;
import model.interfaces.IVideoInfo;

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

        iVideoInfo.setVideoId(videoInfoFx.getVideoId().toString());
        iVideoInfo.setChannelTitle(videoInfoFx.getChannelTitle().toString());
        iVideoInfo.setTitle(videoInfoFx.getTitle().toString());
        iVideoInfo.setTimestamp(videoInfoFx.getTimestamp().toString());
        iVideoInfo.setVideoDescription(videoInfoFx.getVideoDescription().toString());
        //TODO
        //iVideoInfo.setViewCount(new BigInteger(String.valueOf(videoInfoFx.getViewCount())));
        //iVideoInfo.setFavorite(new BigInteger(String.valueOf(videoInfoFx.getFavorite())));
        //iVideoInfo.setLikes(new BigInteger(String.valueOf(videoInfoFx.getLikes())));
        //iVideoInfo.setCommentCount(new BigInteger(String.valueOf(videoInfoFx.getCommentCount())));

        return iVideoInfo;
    }
}
