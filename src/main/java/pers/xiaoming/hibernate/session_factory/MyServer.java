package pers.xiaoming.hibernate.session_factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyServer implements AutoCloseable {
    private static final String CONFIG_NAME = "my.cfg.xml";

    private static SessionFactory sessionFactory;

    private MyServer() {

    }

    public static Session getSession() {
        if (sessionFactory == null) {
            synchronized (Server.class) {
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
