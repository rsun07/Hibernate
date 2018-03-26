package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetByPage;
import pers.xiaoming.hibernate.command.get_interface.GetStudent;
import pers.xiaoming.hibernate.command.get_interface.GetTopTenStudents;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetQueryTest {
    private List<Integer> ids;

    @BeforeClass
    public void setUp() {
        ids = DataProcessor.getIds();
    }

    @Test(dataProvider = "get_student")
    public void testGetStudent(GetStudent getStudent) {
        Student student = getStudent.get(SessionManager.getSession(), ids.get(0));
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
        List<Student> students = getStudents.get(SessionManager.getSession());
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

    @Test(dataProvider = "get_by_page")
    public void testGetByPage(GetByPage getByPage) {
        int pageSize = 2;
        int startIndex = 6;

        List<Student> students = getByPage.get(SessionManager.getSession(), startIndex, pageSize);
        Assert.assertEquals(pageSize, students.size());
    }

    @DataProvider(name = "get_by_page")
    public Object[][] getByPageDataProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetByPageImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetByPageImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetByPageImpl()},
        };
    }
}
