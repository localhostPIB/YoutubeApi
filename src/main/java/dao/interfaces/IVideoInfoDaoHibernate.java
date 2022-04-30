package dao.interfaces;

import model.interfaces.IVideoInfo;

import java.util.List;

public interface IVideoInfoDaoHibernate {

    void saveVideoInfo(final IVideoInfo iVideoInfo);

    List<IVideoInfo> findAllVideoInfos();

    IVideoInfo findVideoInfoById(final int id);

    void deleteVideoInfoById(final int id);


}
