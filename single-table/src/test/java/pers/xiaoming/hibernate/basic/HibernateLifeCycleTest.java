package pers.xiaoming.hibernate.basic;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.basic.CURDStudentBasic;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.MySessionFactory;


public class HibernateLifeCycleTest {

    private int id;

    @Test
    @SuppressWarnings("UnnecessaryLocalVariable")
    public void testTransientToPersistent() throws Exception {
        CURDStudentBasic dbOperator = new CURDStudentBasic();

        Student student = Student.builder().name("Mike").age(26).score(78.0).build();

        Student studentBeforeCreate = student;

        Assert.assertNull(student.getId());

        // here use MySessionFactory, my.cfg.xml with hibernate.properties
        this.id = dbOperator.create(MySessionFactory.getSession(), student);

        Student studentAfterCreate = student;

        // after save() or persist()
        // hibernate life cycle will go from "transient" to "persistent"
        // it will be assigned to the instance
        Assert.assertNotNull(student.getId());
        Assert.assertSame(studentBeforeCreate, studentAfterCreate);
        Assert.assertEquals((Integer) this.id, student.getId());
    }

    @AfterClass
    public void cleanup() throws Exception {
        CURDStudentBasic dbOperator = new CURDStudentBasic();
        dbOperator.delete(MySessionFactory.getSession(), this.id);
    }
}
