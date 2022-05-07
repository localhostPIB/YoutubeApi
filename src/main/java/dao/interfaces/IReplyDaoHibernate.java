package dao.interfaces;

import model.interfaces.IReply;

import java.util.List;

public interface IReplyDaoHibernate {
    void saveReply(final IReply iReply) throws Exception;

    List<IReply> findAllReplies() throws Exception;


}
