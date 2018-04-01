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

public class ReadLockAndRead {
    private static final int TEST_ID = 1;
    private static final Logger logger = LoggerFactory.getLogger(ReadLockAndRead.class.getName());

    /*
        Even though there are threads/transactions already put a PESSIMISTIC_READ lock on the row.
        New thread/transaction can still READ the row.

        2018-04-01 14:30:57 [SecondThread] INFO  p.x.h.c.p.ReadLockAndRead - Get student = Student(id=1, name=John0, age=20, score=81.0), put an PESSIMISTIC_READ lock on it and and sleep
        2018-04-01 14:30:57 [ThirdThread] INFO  p.x.h.c.p.ReadLockAndRead - Get student = Student(id=1, name=John0, age=20, score=81.0), put an PESSIMISTIC_READ lock on it and and sleep
        2018-04-01 14:30:57 [FirstThread] INFO  p.x.h.c.p.ReadLockAndRead - Get student = Student(id=1, name=John0, age=20, score=81.0), put an PESSIMISTIC_READ lock on it and and sleep
        2018-04-01 14:30:57 [ThirdThread] INFO  p.x.h.c.p.ReadLockAndRead - Thread completed!
        2018-04-01 14:30:57 [FirstThread] INFO  p.x.h.c.p.ReadLockAndRead - Thread completed!
        2018-04-01 14:30:57 [SecondThread] INFO  p.x.h.c.p.ReadLockAndRead - Thread completed!
     */
    @Test
    public void test() {
        Thread firstThread = new Thread(this::readTransaction);
        firstThread.setName("FirstThread");
        firstThread.start();

        Thread secondThread = new Thread(this::readTransaction);
        secondThread.setName("SecondThread");
        secondThread.start();

        Thread thirdThread = new Thread(this::readTransaction);
        thirdThread.setName("ThirdThread");
        thirdThread.start();
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

    private void sleep(int milliSecs) {
        try {
            Thread.sleep(milliSecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[] {InitDb.class, ReadLockAndRead.class});
        testNG.run();
    }
}
