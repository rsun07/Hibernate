package pers.xiaoming.hibernate.command.pessimisticlock;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class LockTest {
    private static final int TEST_ID = 1;
    private static final Logger logger = LoggerFactory.getLogger(LockTest.class.getName());

    private Exception ex;

    /*
        2018-04-01 15:55:13 [WriteLock] INFO  p.x.h.c.pessimisticlock.LockTest - write lock start
        2018-04-01 15:55:13 [WriteTransaction] INFO  p.x.h.c.pessimisticlock.LockTest - write Transaction start
        2018-04-01 15:55:13 [READThread] INFO  p.x.h.c.pessimisticlock.LockTest - read thread start
        2018-04-01 15:55:13 [WriteTransaction] INFO  p.x.h.c.pessimisticlock.LockTest - Get student = Student(id=1, name=John0, age=20, score=81.0)
        2018-04-01 15:55:13 [WriteTransaction] INFO  p.x.h.c.pessimisticlock.LockTest - Trying to update student = Student(id=1, name=Mike2, age=20, score=81.0)

        All read and write were blocked during the write lock period

        2018-04-01 15:55:23 [WriteLock] INFO  p.x.h.c.pessimisticlock.LockTest - Get student = Student(id=1, name=John0, age=20, score=81.0)
        2018-04-01 15:55:23 [WriteLock] INFO  p.x.h.c.pessimisticlock.LockTest - Trying to update student = Student(id=1, name=Mike, age=20, score=81.0)
        2018-04-01 15:55:23 [READThread] INFO  p.x.h.c.pessimisticlock.LockTest - Get student for the first time Student(id=1, name=Mike, age=20, score=81.0)
        2018-04-01 15:55:23 [WriteLock] INFO  p.x.h.c.pessimisticlock.LockTest - Thread completed!
        2018-04-01 15:55:23 [READThread] INFO  p.x.h.c.pessimisticlock.LockTest - Get student for the second time Student(id=1, name=Mike, age=20, score=81.0)
        2018-04-01 15:55:23 [READThread] INFO  p.x.h.c.pessimisticlock.LockTest - Thread completed!
        2018-04-01 15:55:23 [WriteTransaction] INFO  p.x.h.c.pessimisticlock.LockTest - Thread completed!
     */
    @Test
    public void test() throws Exception {
        String updatedName = "Mike";
        String failedUpdateName = "Mike2";

        Thread writeThread1 = new Thread(() -> writeLock(updatedName));
        writeThread1.setName("WriteLock");
        writeThread1.start();

        Thread writeThread2 = new Thread(() -> writeTransaction(failedUpdateName));
        writeThread2.setName("WriteTransaction");
        writeThread2.start();

        Thread readThread = new Thread(this::readTransaction);
        readThread.setName("READThread");
        readThread.start();

        sleep(3000);
        if (ex != null) {
            throw ex;
        }
    }

    private void writeLock(String name) {
        logger.info("write lock start");

        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Student student = session.get(Student.class, TEST_ID, LockMode.PESSIMISTIC_WRITE);

            sleep(2000);

            logger.info("Get student = {}", student);

            student.setName(name);

            logger.info("Trying to update student = {}", student);

            session.getTransaction().commit();

        } catch (Exception e) {
            ex = e;
        }
        logger.info("Thread completed!");
    }

    private void writeTransaction(String name) {
        logger.info("write Transaction start");

        sleep(100);

        Session session = SessionManager.getSession();

        try {
            Transaction tran = session.beginTransaction();
            tran.setTimeout(1);

            Student student = session.get(Student.class, TEST_ID);

            logger.info("Get student = {}", student);

            student.setName(name);

            logger.info("Trying to update student = {}", student);

            session.update(student);

            session.getTransaction().commit();

        } catch (Exception e) {
            ex = e;
        }
        logger.info("Thread completed!");
    }


    private void readTransaction() {
        logger.info("read thread start");

        Session session = SessionManager.getSession();

        try {
            Transaction tran = session.beginTransaction();
            tran.setTimeout(1);

            Student student = session.get(Student.class, TEST_ID, LockMode.PESSIMISTIC_READ);

            logger.info("Get student for the first time {}", student);

            sleep(100);

            student = session.get(Student.class, TEST_ID, LockMode.PESSIMISTIC_READ);

            logger.info("Get student for the second time {}", student);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
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
        testNG.setTestClasses(new Class[] {InitDb.class, LockTest.class});
        testNG.run();
    }
}
