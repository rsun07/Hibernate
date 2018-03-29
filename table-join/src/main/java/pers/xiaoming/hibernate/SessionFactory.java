package pers.xiaoming.hibernate;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class SessionFactory implements AutoCloseable {
    private static final String CONFIG_NAME = "hibernate.cfg.xml";
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
        // pass config file name into the configure() function
        Configuration config = new Configuration().configure(CONFIG_NAME);

        org.hibernate.SessionFactory sessionFactory = config.buildSessionFactory();

        return sessionFactory;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
