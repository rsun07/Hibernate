package pers.xiaoming.hibernate.query;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.GetStudent;
import pers.xiaoming.hibernate.command.GetTopTenStudents;
import pers.xiaoming.hibernate.command.hql.BasicGet;
import pers.xiaoming.hibernate.command.hql.Sort;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.Server;

import java.util.List;

public class HqlGetsTest {

    private List<Integer> ids;

    @BeforeClass
    public void setUp() {
        ids = DataProcessor.getIds();
    }

    @Test
    public void testGetStudent() {
        GetStudent getStudent = new BasicGet();
        Student student = getStudent.get(Server.getSession(), ids.get(0));
        Assert.assertTrue(DataProcessor.validateStudent(student));
    }

    @Test
    public void testGetTopTenStudents() {
        GetTopTenStudents getStudents = new Sort();
        List<Student> students = getStudents.get(Server.getSession());
        Assert.assertEquals(10, students.size());

    }
}
