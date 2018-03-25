package pers.xiaoming.hibernate.basic;

import org.testng.Assert;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.basic.CURDStudentBasic;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.Server;


public class HibernateLifeCycleTest {

    @Test
    @SuppressWarnings("UnnecessaryLocalVariable")
    public void testTransientToPersistent() {
        CURDStudentBasic dbOperator = new CURDStudentBasic();

        Student student = Student.builder().name("Mike").age(26).score(78.0).build();

        Student studentBeforeCreate = student;

        Assert.assertNull(student.getId());

        int id = dbOperator.create(Server.getSession(), student);

        Student studentAfterCreate = student;

        // after save() or persist()
        // hibernate life cycle will go from "transient" to "persistent"
        // it will be assigned to the instance
        Assert.assertNotNull(student.getId());
        Assert.assertSame(studentBeforeCreate, studentAfterCreate);
        Assert.assertEquals((Integer) id, student.getId());
    }
}
