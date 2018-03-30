package pers.xiaoming.hibernate.basic;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.basic.CURDStudentBasic;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class StudentCURDTest {
    private static CURDStudentBasic dbOperator;
    private static Student student;
    private static int id;

    @BeforeClass
    public static void init() {
        dbOperator = new CURDStudentBasic();
        student = Student.builder().name("John").age(20).score(88.5).build();
    }

    @Test
    public void testCreate() throws Exception {
        id = dbOperator.create(SessionManager.getSession(), student);
        testGet();
    }

    @Test(dependsOnMethods = "testCreate")
    public void testUpdate() throws Exception {
        student.setAge(22);
        student.setScore(90.5);
        dbOperator.update(SessionManager.getSession(), student);
        testGet();
    }

    @Test(dependsOnMethods = "testUpdate")
    public void testDelete() throws Exception {
        dbOperator.delete(SessionManager.getSession(), id);
        student = null;
        testGet();
    }


    private void testGet() throws Exception {
        Student studentGetFromDb = dbOperator.get(SessionManager.getSession(), id);
        Assert.assertEquals(student, studentGetFromDb);
    }

    @AfterTest
    public void cleanup() throws Exception {
        try {
            dbOperator.delete(SessionManager.getSession(), id);
        } catch (Exception e) {
            // ignore
        }
    }
}
