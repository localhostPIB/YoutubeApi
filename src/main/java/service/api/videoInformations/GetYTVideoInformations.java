package service.api.videoInformations;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import dao.VideoDaoHibernateImp;
import lombok.Getter;
import model.interfaces.IVideoInfo;
import service.api.Auth;
import util.*;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

@Getter
public class GetYTVideoInformations {
    private static String CLIENT_SECRET;

    private VideoDaoHibernateImp videoDaoHibernateImp;

    private IVideoInfo iVideoInfo;


    public GetYTVideoInformations(VideoDaoHibernateImp videoDaoHibernateImp){
        this.videoDaoHibernateImp = videoDaoHibernateImp;

        try {
            CLIENT_SECRET = PropertyUtils.readPropertyFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private YouTube.Videos.List prepareStatistic(String videoId) throws GeneralSecurityException, IOException {
        YouTube.Videos.List listVideosRequest = Auth.getService().videos().list("snippet,statistics");
        listVideosRequest.setId(videoId);
        listVideosRequest.setKey(CLIENT_SECRET);

        return listVideosRequest;
    }

    public void getYTVideoStatistics(final String videoId) throws GeneralSecurityException, IOException {
        VideoListResponse videoListResponse = prepareStatistic(videoId).execute();
        Video video = videoListResponse.getItems().get(0);

        BigInteger viewCount    = video.getStatistics().getViewCount();
        BigInteger likes        = video.getStatistics().getLikeCount();
        BigInteger comments     = video.getStatistics().getCommentCount();
        BigInteger favorite     = video.getStatistics().getFavoriteCount();
        String title            = video.getSnippet().getTitle();
        String channelTitle     = video.getSnippet().getChannelTitle();
        String videoDescription = video.getSnippet().getDescription();

        IVideoInfo iVideoInfo   = StaticModelFactory.getVideoInfoObject(videoId,StaticModelFactory.getActualDate(),
                                                                      viewCount,likes,comments, favorite,
                                                                      title, channelTitle, videoDescription);

        setIVideoInfo(iVideoInfo);
        saveVideoInfos(iVideoInfo);
    }

    private void setIVideoInfo(IVideoInfo iVideoInfo){
        this.iVideoInfo = iVideoInfo;
    }

    private void saveVideoInfos(IVideoInfo iVideoInfo){
        videoDaoHibernateImp.saveVideoInfo(iVideoInfo);
    }
}
