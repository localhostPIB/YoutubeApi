package util.converter;

import javafx.beans.property.SimpleStringProperty;
import model.classes.VideoInfoFx;
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
}
