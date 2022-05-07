package dao.classes;

import dao.interfaces.IYTUserDaoHibernate;
import model.interfaces.IYoutubeUser;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtils;

import java.util.*;

public class YTUserDaoHibernateImp implements IYTUserDaoHibernate {


    public YTUserDaoHibernateImp() {

    }

    public void saveUser(final IYoutubeUser iYoutubeUser) throws Exception {
        Session session = null;

        try {
            session = HibernateUtils.getSession();
            session.beginTransaction();
            session.saveOrUpdate(iYoutubeUser);
            session.flush();
            session.getTransaction().commit();
        } catch (Exception ex) {
           throw new Exception(ex);
        } finally {
            HibernateUtils.closeSession(session);
        }
    }

    public List<IYoutubeUser> findAllYTUsersByVideoId(final String videoId) throws Exception {
        List<IYoutubeUser> userList = new ArrayList<>();
        try {
            findAllYTUsers().forEach(iYoutubeUser -> iYoutubeUser.getIVideoInfoList()
                    .forEach(i -> {
                        if (videoId.equals(i.getVideoId())) {
                            userList.add(iYoutubeUser);
                        }
                    }));
        }catch (Exception ex){
            throw new Exception(ex);
        }

        return userList;
    }

    private List<IYoutubeUser> findAllYTUsers() throws Exception{
        Session session = null;
        List<IYoutubeUser> userList;

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
            throw new Exception(ex);
        }finally{
            HibernateUtils.closeSession(session);
        }

        return userList;
    }
}