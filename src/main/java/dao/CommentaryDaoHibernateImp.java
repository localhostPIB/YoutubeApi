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

    public List<ICommentary> findAllYTCommentaries(){
        Session session = null;
        List<ICommentary> commentaryList  = new ArrayList<>();

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();
            String queryString ="SELECT Commentary FROM Commentary c JOIN FETCH c.iYoutubeUser";
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

