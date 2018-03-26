package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetTopTenStudents;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetToptenStudentsTest {
    @Test(dataProvider = "get_topten_student")
    public void testGetTopTenStudents(GetTopTenStudents getStudents) throws Exception {
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
}
