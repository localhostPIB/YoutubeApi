package dao;

import model.interfaces.IYoutubeUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.*;

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


    public List<IYoutubeUser> findAllYTUsersByVideoId(String videoId){
        Session session = null;
        List<IYoutubeUser> userList  = new ArrayList<>();

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();

            String queryString ="SELECT yU " +
                                "FROM YoutubeUser_VideoInfo yV, YoutubeUser yU " +
                                "WHERE yU.channelId = yV.YoutubeUser_channelId " +
                                "and yV.iVideoInfoList_id ="+"'"+videoId+"'";

            Query query = session.createQuery(queryString);
            userList = (List<IYoutubeUser>) query.getResultList();
            session.flush();
            session.getTransaction().commit();
        }catch (Exception ex){
            ex.fillInStackTrace();
        }finally{
            HibernateUtils.closeSession(session);
        }

        return userList;
    }
}