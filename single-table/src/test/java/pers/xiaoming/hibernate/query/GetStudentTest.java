package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetStudent;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetStudentTest {

    @Test(dataProvider = "get_student")
    public void testGetStudent(GetStudent getStudent) throws Exception {
        Student student = getStudent.get(SessionManager.getSession(), DataProcessor.getIds().get(0));
        Assert.assertTrue(DataProcessor.validateStudent(student));
    }

    @DataProvider(name = "get_student")
    public Object[][] getStudentDataProvider() throws Exception {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetStudentImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetStudentImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetStudentImpl()},
        };
    }
}
