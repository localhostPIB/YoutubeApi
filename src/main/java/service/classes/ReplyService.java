package service.classes;

import dao.classes.ReplyDaoHibernateImp;
import dao.interfaces.IReplyDaoHibernate;
import model.interfaces.IReply;
import service.inferfaces.IReplyService;

import java.util.List;

public class ReplyService implements IReplyService {

    private IReplyDaoHibernate iReplyDaoHibernate;

    public ReplyService(){
        this.iReplyDaoHibernate = new ReplyDaoHibernateImp();
    }

    @Override
    public List<IReply> getRepliesById(final int id) throws Exception {
       return this.iReplyDaoHibernate.findRepliesById(id);
    }
}
