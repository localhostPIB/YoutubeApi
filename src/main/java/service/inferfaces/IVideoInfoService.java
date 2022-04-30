package service.inferfaces;

import model.interfaces.IVideoInfo;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IVideoInfoService {

    void getVideoInformations(final IVideoInfo iVideoInfo) throws GeneralSecurityException, IOException;

    IVideoInfo callVideoInformations(final String videoId) throws GeneralSecurityException, IOException;

    void deleteVideoInfoById(final int id);

    List<IVideoInfo> getAllVideoInfos();

    void initClientId() throws FileNotFoundException;

    String getClientId();
}
