package dao.interfaces;

import model.interfaces.IVideoInfo;
import java.util.List;

public interface IVideoInfoDaoHibernate {

    void saveVideoInfo(final IVideoInfo iVideoInfo) throws Exception;

    List<IVideoInfo> findAllVideoInfos() throws Exception;

    IVideoInfo findVideoInfoById(final int id) throws Exception;

    void deleteVideoInfoById(final int id) throws Exception;


}
