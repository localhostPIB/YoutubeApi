package dao.interfaces;

import model.interfaces.IReply;

import java.util.List;

public interface IReplyDaoHibernate {
    void saveReply(final IReply iReply) throws Exception;

    List<IReply> findAllReplies() throws Exception;

    List<IReply> findRepliesById(final int id) throws Exception;

    void deleteRepliesById(final int videoId) throws Exception;
}
