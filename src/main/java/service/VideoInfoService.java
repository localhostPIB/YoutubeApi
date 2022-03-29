package service;

import dao.VideoDaoHibernateImp;
import model.interfaces.IVideoInfo;
import service.api.videoInformations.GetYTVideoInformations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class VideoInfoService {

    private GetYTVideoInformations getYTVideoInformations;

    private VideoDaoHibernateImp videoDaoHibernateImp;

    public VideoInfoService() {
        this.videoDaoHibernateImp = new VideoDaoHibernateImp();
        this.getYTVideoInformations = new GetYTVideoInformations(this.videoDaoHibernateImp);
    }

    public void getVideoInformations(String videoId) throws GeneralSecurityException, IOException {
        this.getYTVideoInformations.getYTVideoStatistics(videoId);
    }

    public List<IVideoInfo> getAllVideoInfos(){
       return this.videoDaoHibernateImp.findAllVideoInfos();
    }

    public void initClientId() throws FileNotFoundException {
        this.getYTVideoInformations.initClientSecret();
    }

    public String getClientId(){
        return this.getYTVideoInformations.getClientSecret();
    }

}
