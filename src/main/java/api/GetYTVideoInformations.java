package api;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import dao.VideoDaoHibernateImp;
import model.interfaces.IVideoInfo;
import util.*;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

public class GetYTVideoInformations {
    private static String CLIENT_SECRET;

    private VideoDaoHibernateImp videoDaoHibernateImp;


    public GetYTVideoInformations(VideoDaoHibernateImp videoDaoHibernateImp){
        this.videoDaoHibernateImp = videoDaoHibernateImp;

        try {
            CLIENT_SECRET = PropertyUtils.readPropertyFile();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private YouTube.Videos.List prepareStatistic(String videoId) throws GeneralSecurityException, IOException {
        YouTube.Videos.List listVideosRequest = Auth.getService().videos().list("statistics");
        listVideosRequest.setId(videoId);
        listVideosRequest.setKey(CLIENT_SECRET);
       // VideoListResponse listResponse = listVideosRequest.execute();
        return listVideosRequest;
    }

    public void getYTVideoStatistics(String videoId) throws GeneralSecurityException, IOException {
        VideoListResponse videoListResponse = prepareStatistic(videoId).execute();
        Video video = videoListResponse.getItems().get(0);

        BigInteger viewCount = video.getStatistics().getViewCount();
        BigInteger likes     = video.getStatistics().getLikeCount();
        BigInteger comments  = video.getStatistics().getCommentCount();
        BigInteger favorite  = video.getStatistics().getFavoriteCount();

        IVideoInfo iVideoInfo = StaticModelFactory.getVideoInfoObject(videoId,StaticModelFactory.getActualDate(),
                                                                      viewCount,likes,comments,favorite);
        saveVideoInfos(iVideoInfo);

    }

    private void saveVideoInfos(IVideoInfo iVideoInfo){
        videoDaoHibernateImp.saveVideoInfo(iVideoInfo);
    }
}
