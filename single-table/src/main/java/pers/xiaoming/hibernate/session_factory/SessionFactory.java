package pers.xiaoming.hibernate.session_factory;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class SessionFactory implements AutoCloseable {
    private static org.hibernate.SessionFactory sessionFactory;

    private SessionFactory() {

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
    private static org.hibernate.SessionFactory initSession() {
        Configuration config = new Configuration().configure();

        org.hibernate.SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
