package dao;

import model.interfaces.ICommentary;
import org.hibernate.Session;
import util.HibernateUtils;

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
}
