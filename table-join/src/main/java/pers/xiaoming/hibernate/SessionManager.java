package pers.xiaoming.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager implements AutoCloseable {
    private static final String CONFIG_NAME = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    private SessionManager() {

    }

    public static Session getSession() {
        if (sessionFactory == null) {
            synchronized (SessionManager.class) {
                sessionFactory = initSession();
            }
        }
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private static SessionFactory initSession() {
        // pass config file name into the configure() function
        Configuration config = new Configuration().configure(CONFIG_NAME);

        SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
