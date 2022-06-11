package dao.classes;

import dao.interfaces.IReplyDaoHibernate;
import model.interfaces.ICommentary;
import model.interfaces.IReply;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.*;

public class ReplyDaoHibernateImp implements IReplyDaoHibernate {

    public void saveReply(final IReply iReply) throws Exception{
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(iReply);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<IReply> findAllReplies() throws Exception{
        Session session = null;
        List<IReply> replyList;

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();
            String queryString ="SELECT r FROM Reply r";
            Query query = session.createQuery(queryString);
            replyList = (List<IReply>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            throw new Exception(ex);
        }finally{
            HibernateUtils.closeSession(session);
        }

        return replyList;
    }

    public List<IReply> findRepliesById(final int id) throws Exception{
        Session session = null;
        List<IReply> replyList;

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();

            String queryString ="SELECT c.iReplyList " +
                    "FROM Commentary c " +
                    "WHERE c.id =" +"'"+id+"'";

            Query query = session.createQuery(queryString);
            replyList = (List<IReply>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            throw new Exception(ex);
        }finally{
            HibernateUtils.closeSession(session);
        }

        return replyList;
    }

    @Override
    public void deleteRepliesById(final int videoId) throws Exception {
        Session session = null;
        List<IReply> iReplyList = findRepliesById(videoId);

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();

            for(IReply iReply : iReplyList){
                session.delete(iReply);
            }
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            throw new Exception(ex);
        }finally{
            HibernateUtils.closeSession(session);
        }
    }
    
}
