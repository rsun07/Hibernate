package pers.xiaoming.hibernate;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class SingleTableTest {
    @Test
    public void test() {
        Session session = SessionManager.getSession();

        int testId = 1;
        int testDbId = testId + 1;

        Student studentInit = InitDb.getStudent(testId);

        try {
            session.beginTransaction();
            Student studentReturned = session.get(Student.class, testDbId);

            Assert.assertEquals(studentReturned.getName(), studentInit.getName());
            Assert.assertEquals(studentReturned.getAge(), studentInit.getAge());

            // because the @Transient annotation in score field
            Assert.assertNotEquals(studentReturned.getScore(), studentInit.getScore());
            Assert.assertNull(studentReturned.getScore());

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
