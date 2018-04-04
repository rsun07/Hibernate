package pers.xiaoming.hibernate.basic;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

@Slf4j
public class SessionTest {
    private static Session initession;

    @BeforeClass
    public static void init() {
        Session session = SessionManager.getSession();
        initession = session;
    }

    @Test
    public void getSession() {
        Session session = SessionManager.getSession();
        Assert.assertSame(session, initession);
    }

    @Test
    public void getSessionAfterQuery() {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();
            session.get(Student.class, 1);
        } catch (Exception e) {
            // ignore
        }

        Assert.assertNotEquals(session, initession);

        Session newSession = SessionManager.getSession();
        Assert.assertNotEquals(newSession, initession);
        Assert.assertNotEquals(newSession, session);
    }
}
