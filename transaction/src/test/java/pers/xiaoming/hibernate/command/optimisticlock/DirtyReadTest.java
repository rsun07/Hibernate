package pers.xiaoming.hibernate.command.optimisticlock;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.entity.StudentVersion;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class DirtyReadTest {
    private static final int TEST_ID = 1;
    private static final Logger logger = LoggerFactory.getLogger(DirtyReadTest.class.getName());

    @Test
    public void test() {
        Thread readThread = new Thread(this::readTransaction);
        readThread.setName("ReadThread");
        readThread.start();

        Thread updateThread = new Thread(this::updateTransaction);
        updateThread.setName("UpdateThread");
        updateThread.start();
    }

    private void readTransaction() {
        logger.info("read thread start");

        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            StudentVersion before = session.get(StudentVersion.class, TEST_ID);

            logger.info("read thread get for the first time and sleep");

            sleep(200);

            StudentVersion after = session.get(StudentVersion.class, TEST_ID);

            logger.info("read thread get for the second time and compare");

            Assert.assertEquals(after, before);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        logger.info("read thread completed!");
    }

    private void updateTransaction() {
        logger.info("update thread start and sleep");

        sleep(100);

        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            StudentVersion student = session.get(StudentVersion.class, TEST_ID);

            student.setName("Mike");

            session.update(student);

            logger.info("update thread update and sleep");

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        logger.info("update thread completed!");
    }

    private void sleep(int milliSecs) {
        try {
            Thread.sleep(milliSecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
