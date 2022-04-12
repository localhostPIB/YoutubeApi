package service.classes.threading;

import service.classes.api.videoInformations.GetYTVideoInformations;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class ServiceThread implements Runnable {

    private final GetYTVideoInformations getYTVideoInformations;

    private final String videoId;

    public ServiceThread(String videoId, GetYTVideoInformations getYTVideoInformations)  {
        this.getYTVideoInformations = getYTVideoInformations;
        this.videoId = videoId;
    }

    @Override
    public void run() {
        try {
            System.out.println("Anzahl Threads: "+java.lang.Thread.activeCount());
            this.getYTVideoInformations.getYTVideoStatistics(this.videoId);

        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
