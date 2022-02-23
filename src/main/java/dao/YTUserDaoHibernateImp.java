package dao;

import model.interfaces.IYoutubeUser;
import org.hibernate.Session;
import util.HibernateUtils;

public class YTUserDaoHibernateImp {


    public YTUserDaoHibernateImp() {

    }

    public void saveUser(IYoutubeUser iYoutubeUser) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(iYoutubeUser);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}