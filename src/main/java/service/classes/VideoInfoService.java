package service.classes;

import dao.classes.VideoInfoDaoHibernateImp;
import dao.interfaces.IVideoInfoDaoHibernate;
import model.interfaces.IVideoInfo;
import service.classes.api.videoInformations.GetYTVideoInformations;
import service.inferfaces.IVideoInfoService;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

public class VideoInfoService implements IVideoInfoService {

    private final GetYTVideoInformations getYTVideoInformations;

    private final IVideoInfoDaoHibernate iVideoInfoDaoHibernate;

    public VideoInfoService() {
        this.iVideoInfoDaoHibernate = new VideoInfoDaoHibernateImp();
        this.getYTVideoInformations = new GetYTVideoInformations(this.iVideoInfoDaoHibernate);
    }

    public void getVideoInformations(final IVideoInfo iVideoInfo) throws GeneralSecurityException, IOException {
       // Runnable runnable = new ServiceThread(iVideoInfo, this.getYTVideoInformations);
       // new Thread(runnable).start();
        this.getYTVideoInformations.getYTVideoStatistics(iVideoInfo);
    }

    public IVideoInfo callVideoInformations(final String videoId) throws GeneralSecurityException, IOException {
       return this.getYTVideoInformations.callYTVideoStatistics(videoId);
    }

    public void deleteVideoInfoById(final int id){
        this.iVideoInfoDaoHibernate.deleteVideoInfoById(id);
    }

    public List<IVideoInfo> getAllVideoInfos(){
       return this.iVideoInfoDaoHibernate.findAllVideoInfos();
    }

    public void initClientId() throws FileNotFoundException {
        this.getYTVideoInformations.initClientSecret();
    }

    public String getClientId(){
        return this.getYTVideoInformations.getClientSecret();
    }
}
