package pers.xiaoming.hibernate.command.optimisticlock;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.entity.StudentVersion;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class TwoThreadUpdateTest {
    private static final int TEST_ID = 1;
    private static final Logger logger = LoggerFactory.getLogger(TwoThreadUpdateTest.class.getName());
    private Exception ex;

    @Test(expectedExceptions = org.hibernate.StaleStateException.class)
    public void test() throws Exception {
        Thread first = new Thread(this::firstUpdate);
        first.setName("FirstUpdateThread");
        first.start();

        Thread second = new Thread(this::secondUpdate);
        second.setName("SecondUpdateThread");
        second.start();

        sleep(1000);

        if (ex != null) {
            throw ex;
        }
    }

    private void firstUpdate() {
        logger.info("First thread start");

        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            StudentVersion student = session.get(StudentVersion.class, TEST_ID);

            logger.info("First thread get student = {} for the first time and sleep", student);

            sleep(200);

            student.setName("Mike2");

            logger.info("First thread trying to update student to {}", student);

            session.update(student);

            // could not reach here because the version number should already increased by the second thread
            assert false;

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            ex = e;
        }
        logger.info("First thread completed!");
    }

    private void secondUpdate() {
        logger.info("Second thread start and sleep");

        sleep(100);

        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            StudentVersion student = session.get(StudentVersion.class, TEST_ID);

            logger.info("Second thread get student = {} for the first time and sleep", student);

            student.setName("Mike");

            logger.info("Second thread trying to update student to {}", student);

            session.update(student);

            logger.info("Second thread updated student to {}", session.get(StudentVersion.class, TEST_ID));

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            ex = e;
        }
        logger.info("Second thread completed!");
    }

    private void sleep(int milliSecs) {
        try {
            Thread.sleep(milliSecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[] {InitDb.class, TwoThreadUpdateTest.class});
        testNG.run();
    }

}
