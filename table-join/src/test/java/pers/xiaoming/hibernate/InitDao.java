package pers.xiaoming.hibernate;

import org.testng.annotations.BeforeSuite;

public class InitDao {
    @BeforeSuite
    public static void initDao() {
        SessionFactory.getSession();
    }
}
