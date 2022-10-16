package dao.classes;

import dao.interfaces.ICommentaryDaoHibernate;
import model.interfaces.ICommentary;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.List;

public class CommentaryDaoHibernateImp implements ICommentaryDaoHibernate {

    @Override
    public void saveCommentary(final ICommentary iCommentary) throws Exception {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(iCommentary);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    @Override
    public List<ICommentary> findAllYTCommentariesByVideoId(final String videoId) throws Exception{
        Session session = null;
        List<ICommentary> commentaryList;

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();

            String queryString ="SELECT c " +
                                "FROM Commentary c " +
                                "WHERE c.iVideoInfo.videoId=" +"'"+videoId+"'";

            Query query = session.createQuery(queryString);
            commentaryList = (List<ICommentary>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            throw new Exception(ex);
        }finally{
            HibernateUtils.closeSession(session);
        }

        return commentaryList;
    }

    @Override
    public void deleteCommentaryById(final String videoId) throws Exception {
        Session session = null;
        List<ICommentary> iCommentaryList = findAllYTCommentariesByVideoId(videoId);

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();

            for(ICommentary iCommentary : iCommentaryList){
                session.delete(iCommentary);
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

