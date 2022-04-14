package service.inferfaces;

import model.interfaces.IVideoInfo;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IVideoInfoService {

    void getVideoInformations(IVideoInfo iVideoInfo);

    IVideoInfo callVideoInformations(String videoId) throws GeneralSecurityException, IOException;

    List<IVideoInfo> getAllVideoInfos();

    void initClientId() throws FileNotFoundException;

    String getClientId();
}
