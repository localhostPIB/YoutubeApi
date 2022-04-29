package dao;

import model.classes.VideoInfo;
import model.interfaces.IVideoInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.*;

public class VideoDaoHibernateImp {

    public void saveVideoInfo(final IVideoInfo iVideo) {
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

    public List<IVideoInfo> findAllVideoInfos() {
        Session session = null;
        List<IVideoInfo> videoInfoList = new ArrayList<>();

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            String queryString = "SELECT v FROM VideoInfo v";
            Query query = session.createQuery(queryString);
            videoInfoList = (List<IVideoInfo>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }

        return videoInfoList;
    }

    public IVideoInfo findVideoInfoById(final int id){
            Session session = null;

            try {
                session = HibernateUtils.getSession();
                session.beginTransaction();
                IVideoInfo iBand = (IVideoInfo) session.get(VideoInfo.class, id);
                session.flush();
                session.getTransaction().commit();

                return iBand;
            } catch (Exception ex) {
                ex.fillInStackTrace();
            } finally {
                HibernateUtils.closeSession(session);
            }

            return null;
        }

    public void deleteVideoInfoById(final int id) {
        Session session = null;
        IVideoInfo iVideoInfo = findVideoInfoById(id);
        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.delete(iVideoInfo);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.fillInStackTrace();
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}