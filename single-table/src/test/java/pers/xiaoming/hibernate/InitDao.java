package pers.xiaoming.hibernate;

import org.testng.annotations.BeforeSuite;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class InitDao {
    @BeforeSuite
    public static void initDao() {
        SessionManager.getSession();
    }
}
