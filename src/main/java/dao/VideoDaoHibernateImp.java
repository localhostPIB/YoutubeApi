package dao;

import model.interfaces.IVideoInfo;
import org.hibernate.Session;
import util.HibernateUtils;

public class VideoDaoHibernateImp {

    public void saveVideo(IVideoInfo iVideo) {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(iVideo);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}
