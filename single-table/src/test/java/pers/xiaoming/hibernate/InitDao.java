package pers.xiaoming.hibernate;

import org.testng.annotations.BeforeSuite;
import pers.xiaoming.hibernate.session_factory.SessionFactory;

public class InitDao {
    @BeforeSuite
    public static void initDao() {
        SessionFactory.getSession();
    }
}
