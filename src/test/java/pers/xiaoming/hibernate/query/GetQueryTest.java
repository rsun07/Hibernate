package pers.xiaoming.hibernate.query;

import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetStudent;
import pers.xiaoming.hibernate.command.get_interface.GetTopTenStudents;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.Server;

import java.util.List;

public class GetQueryTest {
    private List<Integer> ids;

    @BeforeClass
    public void setUp() {
        ids = DataProcessor.getIds();
    }

    @Test(dataProvider = "get_student")
    public void testGetStudent(GetStudent getStudent) {
        Student student = getStudent.get(Server.getSession(), ids.get(0));
        Assert.assertTrue(DataProcessor.validateStudent(student));
    }

    @DataProvider(name = "get_student")
    public Object[][] getStudentDataProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetStudentImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetStudentImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetStudentImpl()},
        };
    }

    @Test(dataProvider = "get_topten_student")
    public void testGetTopTenStudents(GetTopTenStudents getStudents) {
        List<Student> students = getStudents.get(Server.getSession());
        Assert.assertEquals(10, students.size());
    }

    @DataProvider(name = "get_topten_student")
    public Object[][] getTopTenStudentDataProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetTopTenStudentsImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetTopTenStudentsImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetTopTenStudentsImpl()},
        };
    }
}
