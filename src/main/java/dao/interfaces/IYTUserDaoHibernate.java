package dao.interfaces;

import model.interfaces.IYoutubeUser;

import java.util.List;

public interface IYTUserDaoHibernate {
    void saveUser(final IYoutubeUser iYoutubeUser) throws Exception;

    List<IYoutubeUser> findAllYTUsersByVideoId(final String videoId) throws Exception;


}
