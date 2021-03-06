package service.classes.api.videoInformations.classes;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import dao.interfaces.IVideoInfoDaoHibernate;
import lombok.Getter;
import model.interfaces.IVideoInfo;
import service.classes.api.Auth;
import util.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;

@Getter
public class GetYTVideoInformations {
    private static String CLIENT_SECRET;

    private final IVideoInfoDaoHibernate iVideoInfoDaoHibernate;

    private IVideoInfo iVideoInfo;


    public GetYTVideoInformations(IVideoInfoDaoHibernate iVideoInfoDaoHibernate){
        this.iVideoInfoDaoHibernate = iVideoInfoDaoHibernate;
    }

    public void initClientSecret() throws FileNotFoundException {
        try {
            CLIENT_SECRET = PropertyUtils.readPropertyFile();

        } catch (FileNotFoundException fileNotFoundException){
            PropertyUtils.writeEmptyFile();
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

    public void getYTVideoStatistics(final IVideoInfo iVideoInfo) throws Exception {
        saveVideoInfos(iVideoInfo);
    }

    public IVideoInfo callYTVideoStatistics(final String videoId) throws GeneralSecurityException, IOException {
        VideoListResponse videoListResponse = prepareStatistic(videoId).execute();
        Video video = videoListResponse.getItems().get(0);

        BigInteger viewCount    = video.getStatistics().getViewCount();
        BigInteger likes        = video.getStatistics().getLikeCount();
        BigInteger comments     = video.getStatistics().getCommentCount();
        BigInteger favorite     = video.getStatistics().getFavoriteCount();
        String title            = video.getSnippet().getTitle();
        String channelTitle     = video.getSnippet().getChannelTitle();
        String videoDescription = video.getSnippet().getDescription();
        String thumbnailDetails = video.getSnippet().getThumbnails().getMedium().getUrl();

        IVideoInfo iVideoInfo   = StaticModelFactory.getVideoInfoObject(videoId,StaticModelFactory.getActualDate(),
                                                                      viewCount,likes,comments, favorite,
                                                                      title, channelTitle, videoDescription, thumbnailDetails);

        setIVideoInfo(iVideoInfo);

        return iVideoInfo;
    }

    public String getClientSecret(){
        return this.CLIENT_SECRET;
    }

    private void setIVideoInfo(IVideoInfo iVideoInfo){
        this.iVideoInfo = iVideoInfo;
    }

    private void saveVideoInfos(IVideoInfo iVideoInfo) throws Exception {
        try {
            iVideoInfoDaoHibernate.saveVideoInfo(iVideoInfo);
        }catch (Exception ex){
            throw new Exception(ex);
        }
    }
}
