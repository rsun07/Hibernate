package pers.xiaoming.hibernate.session_factory;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class MySessionFactory implements AutoCloseable {
    private static final String CONFIG_NAME = "my.cfg.xml";

    private static org.hibernate.SessionFactory sessionFactory;

    private MySessionFactory() {

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
