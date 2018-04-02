package pers.xiaoming.hibernate.session_factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager implements AutoCloseable {
    private static SessionFactory sessionFactory;

    private SessionManager() {

    }

    public static Session getSession() {
        if (sessionFactory == null) {
            synchronized (SessionFactory.class) {
                sessionFactory = initSession();
            }
        }
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private static SessionFactory initSession() {
        Configuration config = new Configuration().configure();

        SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
