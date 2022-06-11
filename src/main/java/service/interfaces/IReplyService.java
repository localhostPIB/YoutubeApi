package service.interfaces;


import model.interfaces.IReply;

import java.util.List;

public interface IReplyService {

    List<IReply> getRepliesById(final int id) throws Exception;

    void deleteRepliesById(final int id) throws Exception;
}
