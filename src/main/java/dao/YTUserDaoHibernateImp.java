package dao;

import model.interfaces.IYoutubeUser;
import org.hibernate.Session;
import util.HibernateUtils;

import java.util.List;

public class YTUserDaoHibernateImp {

    private List<IYoutubeUser> ytUserList;

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