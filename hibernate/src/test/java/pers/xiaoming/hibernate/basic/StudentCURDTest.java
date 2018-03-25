package pers.xiaoming.hibernate.basic;

import org.hibernate.Session;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.basic.CURDStudentBasic;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.Server;

public class StudentCURDTest {
    private static CURDStudentBasic dbOperator;

    @BeforeTest
    public static void init() {
        dbOperator = new CURDStudentBasic();
    }

    @Test
    public void testCreateAndDelete() {
        Student student = Student.builder().name("John").age(20).score(88.5).build();

        int id = create(getMySession(), student);
        student.setId(id);

        Student studentGetFromDb = get(getSession(),id);
        Assert.assertEquals(student, studentGetFromDb);

        delete(getSession(),id);

        studentGetFromDb = get(getSession(),id);
        Assert.assertNull(studentGetFromDb);
    }

    private int create(Session session, Student student) {
        return dbOperator.create(session, student);
    }

    private Student get(Session session, int id) {
        return dbOperator.get(session, id);
    }

    private void delete(Session session, int id) {
        dbOperator.delete(session, id);
    }

    private Session getSession() {
        return Server.getSession();
    }

    private Session getMySession() {
        return Server.getSession();
    }
}
