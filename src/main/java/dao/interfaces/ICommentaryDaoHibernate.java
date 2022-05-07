package dao.interfaces;

import model.interfaces.ICommentary;

import java.util.List;

public interface ICommentaryDaoHibernate {
    void saveCommentary(final ICommentary iCommentary) throws Exception;

    List<ICommentary> findAllYTCommentariesByVideoId(final String videoId) throws Exception;


}
