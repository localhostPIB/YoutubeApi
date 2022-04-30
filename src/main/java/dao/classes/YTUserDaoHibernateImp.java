package dao.classes;

import model.interfaces.IYoutubeUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.*;

public class YTUserDaoHibernateImp {


    public YTUserDaoHibernateImp() {

    }

    public void saveUser(final IYoutubeUser iYoutubeUser) {
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

    public List<IYoutubeUser> findAllYTUsersByVideoId(final String videoId){
        List<IYoutubeUser> userList = new ArrayList<>();
        findAllYTUsers().forEach(iYoutubeUser -> iYoutubeUser.getIVideoInfoList()
                        .forEach(i -> {if(videoId.equals(i.getVideoId())){ userList.add(iYoutubeUser);}}));

        return userList;
    }

    private List<IYoutubeUser> findAllYTUsers(){
        Session session = null;
        List<IYoutubeUser> userList  = new ArrayList<>();

        try{
            session = HibernateUtils.getSession();
            session.beginTransaction();

            String queryString ="SELECT yU " +
                                "FROM YoutubeUser yU";

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