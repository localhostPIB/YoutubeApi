package dao;

import model.interfaces.ICommentary;
import model.interfaces.IYoutubeUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class CommentaryDaoHibernateImp {

    public void saveCommentary(ICommentary iCommentary) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(iCommentary);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<ICommentary> findAllYTCommentariesByVideoId(String videoId){
        Session session = null;
        List<ICommentary> commentaryList  = new ArrayList<>();

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
            ex.fillInStackTrace();
        }finally{
            HibernateUtils.closeSession(session);
        }

        return commentaryList;
    }
}

