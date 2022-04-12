package service.classes;

import dao.VideoDaoHibernateImp;
import model.interfaces.IVideoInfo;
import service.classes.api.videoInformations.GetYTVideoInformations;
import service.classes.threading.ServiceThread;
import service.inferfaces.IVideoInfoService;

import java.io.*;
import java.util.List;

public class VideoInfoService implements IVideoInfoService {

    private final GetYTVideoInformations getYTVideoInformations;

    private final VideoDaoHibernateImp videoDaoHibernateImp;

    public VideoInfoService() {
        this.videoDaoHibernateImp = new VideoDaoHibernateImp();
        this.getYTVideoInformations = new GetYTVideoInformations(this.videoDaoHibernateImp);
    }

    public void getVideoInformations(String videoId) {
        Runnable runnable = new ServiceThread(videoId, this.getYTVideoInformations);
        new Thread(runnable).start();
        //this.getYTVideoInformations.getYTVideoStatistics(videoId);
        System.out.println("Anzahl Threads: Serviceklasse: "+java.lang.Thread.activeCount());
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
