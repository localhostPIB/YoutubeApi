package service.interfaces;

import model.interfaces.IVideoInfo;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.List;

public interface IVideoInfoService {

    void getVideoInformations(final IVideoInfo iVideoInfo) throws Exception;

    IVideoInfo callVideoInformations(final String videoId) throws GeneralSecurityException, IOException;

    void deleteVideoInfoById(final int id) throws Exception;

    void createVideoInfosAsCSV(final File file) throws Exception;

    void createVideoInfosAsHTML(final File file, String docName) throws IOException;

    void createVideoInfosAsPDF(final File file, String docName) throws IOException;

    List<IVideoInfo> getAllVideoInfos() throws Exception;

    void initClientId() throws FileNotFoundException;

    String getClientId();
}
