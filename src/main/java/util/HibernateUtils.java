package util;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;


/**
 * Verwaltet (z.b erstellt) die Verbindung zur Datenbank mit Hibernate
 */
public class HibernateUtils {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed:\t" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() {

        return getSessionFactory().getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    public static void closeSession(final Session session) {
        try {
            if ((session != null) && (session.isOpen())) {
                session.close();
            }
        }
        catch (HibernateException he) {
            throw new HibernateException(he);
        }
    }

}