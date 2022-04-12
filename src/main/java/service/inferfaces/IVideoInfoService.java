package service.inferfaces;

import model.interfaces.IVideoInfo;

import java.io.FileNotFoundException;
import java.util.List;

public interface IVideoInfoService {

    void getVideoInformations(String videoId);

    List<IVideoInfo> getAllVideoInfos();

    void initClientId() throws FileNotFoundException;

    String getClientId();
}
