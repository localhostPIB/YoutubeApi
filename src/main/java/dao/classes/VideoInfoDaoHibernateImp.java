package dao.classes;

import dao.interfaces.IVideoInfoDaoHibernate;
import model.classes.VideoInfo;
import model.interfaces.IVideoInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;
import util.validator.ObjectValidator;

import java.util.*;

public class VideoInfoDaoHibernateImp implements IVideoInfoDaoHibernate {

    public void saveVideoInfo(final IVideoInfo iVideoInfo) throws Exception{
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            if(ObjectValidator.validateVideoInfoObject(iVideoInfo)) {
                session.saveOrUpdate(iVideoInfo);
                session.flush();
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<IVideoInfo> findAllVideoInfos() throws Exception{
        Session session = null;
        List<IVideoInfo> videoInfoList;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            String queryString = "SELECT v FROM VideoInfo v";
            Query query = session.createQuery(queryString);
            videoInfoList = (List<IVideoInfo>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            HibernateUtils.closeSession(session);
        }

        return videoInfoList;
    }

    public IVideoInfo findVideoInfoById(final int id) throws Exception{
            Session session = null;

            try {
                session = HibernateUtils.getSession();
                session.beginTransaction();
                IVideoInfo iVideoInfo = (IVideoInfo) session.get(VideoInfo.class, id);
                session.flush();
                session.getTransaction().commit();

                return iVideoInfo;
            } catch (Exception ex) {
                throw new Exception(ex);
            } finally {
                HibernateUtils.closeSession(session);
            }
        }

    public void deleteVideoInfoById(final int id) throws Exception {
        Session session = null;
        IVideoInfo iVideoInfo = findVideoInfoById(id);
        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();

            if(ObjectValidator.validateVideoInfoObject(iVideoInfo)) {
                session.delete(iVideoInfo);
                session.flush();
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }
}