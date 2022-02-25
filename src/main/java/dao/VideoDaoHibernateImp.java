package dao;

import model.interfaces.IVideoInfo;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.*;

public class VideoDaoHibernateImp {

    public void saveVideoInfo(IVideoInfo iVideo) {
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

    public List<IVideoInfo> findAllReplies(){
        Session session = null;
        List<IVideoInfo> videoInfoList  = new ArrayList<>();

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();
            String queryString ="SELECT v FROM VideoInfo v";
            Query query = session.createQuery(queryString);
            videoInfoList = (List<IVideoInfo>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.fillInStackTrace();
        }finally{
            HibernateUtils.closeSession(session);
        }

        return videoInfoList;
    }
}
