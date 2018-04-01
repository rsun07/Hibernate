package pers.xiaoming.hibernate.command.pessimisticlock;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class ReadLockAndWrite {
    private static final int TEST_ID = 1;
    private static final Logger logger = LoggerFactory.getLogger(ReadLockAndWrite.class.getName());

    private Exception ex;

    @Test(timeOut = 2000)
    public void test() throws Exception {
        Thread readThread = new Thread(this::readTransaction);
        readThread.setName("READThread");
        readThread.start();

        Thread writeThread = new Thread(this::writeTransaction);
        writeThread.setName("WRITEThread");
        writeThread.start();

        sleep(500);
        if (ex != null) {
            throw ex;
        }
    }

    private void readTransaction() {
        logger.info("read thread start");

        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Student student = session.get(Student.class, TEST_ID, LockMode.PESSIMISTIC_READ);

            logger.info("Get student = {}, put an PESSIMISTIC_READ lock on it and and sleep", student);

            sleep(200);

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
        logger.info("Thread completed!");
    }

    private void writeTransaction() {
        logger.info("write thread start");

        sleep(100);

        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Student student = session.get(Student.class, TEST_ID);

            logger.info("Get student = {}", student);

            student.setName("Mike");

            logger.info("Trying to update student = {}", student);

            session.update(student);

            assert false;
            session.getTransaction().commit();

        } catch (Exception e) {
            ex = e;
        }
        logger.info("Thread completed!");
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
        testNG.setTestClasses(new Class[] {InitDb.class, ReadLockAndWrite.class});
        testNG.run();
    }
}
