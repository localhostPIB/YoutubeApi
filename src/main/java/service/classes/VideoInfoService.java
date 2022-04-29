package service.classes;

import dao.VideoDaoHibernateImp;
import model.interfaces.IVideoInfo;
import service.classes.api.videoInformations.GetYTVideoInformations;
import service.inferfaces.IVideoInfoService;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

public class VideoInfoService implements IVideoInfoService {

    private final GetYTVideoInformations getYTVideoInformations;

    private final VideoDaoHibernateImp videoDaoHibernateImp;

    public VideoInfoService() {
        this.videoDaoHibernateImp = new VideoDaoHibernateImp();
        this.getYTVideoInformations = new GetYTVideoInformations(this.videoDaoHibernateImp);
    }

    public void getVideoInformations(IVideoInfo iVideoInfo) throws GeneralSecurityException, IOException {
       // Runnable runnable = new ServiceThread(iVideoInfo, this.getYTVideoInformations);
       // new Thread(runnable).start();
        this.getYTVideoInformations.getYTVideoStatistics(iVideoInfo);
    }

    public IVideoInfo callVideoInformations(String videoId) throws GeneralSecurityException, IOException {
       return this.getYTVideoInformations.callYTVideoStatistics(videoId);
    }

    public void deleteVideoInfoById(int id){
        this.videoDaoHibernateImp.deleteVideoInfoById(id);
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
