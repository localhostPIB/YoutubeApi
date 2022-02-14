package dao;

import model.interfaces.IReply;
import org.hibernate.Session;
import util.HibernateUtils;

public class ReplyDaoHibernateImp {

    public void saveReply(IReply iReply) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(iReply);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
