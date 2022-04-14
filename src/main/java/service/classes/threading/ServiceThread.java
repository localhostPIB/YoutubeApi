package service.classes.threading;

import model.interfaces.IVideoInfo;
import service.classes.api.videoInformations.GetYTVideoInformations;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ServiceThread implements Runnable {

    private final GetYTVideoInformations getYTVideoInformations;

    private final IVideoInfo iVideoInfo;

    public ServiceThread(IVideoInfo iVideoInfo, GetYTVideoInformations getYTVideoInformations)  {
        this.getYTVideoInformations = getYTVideoInformations;
        this.iVideoInfo = iVideoInfo;
    }

    @Override
    public void run() {
        try {
            this.getYTVideoInformations.getYTVideoStatistics(this.iVideoInfo);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
