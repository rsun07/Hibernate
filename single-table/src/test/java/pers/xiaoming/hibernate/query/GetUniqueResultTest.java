package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetUniqueResult;
import pers.xiaoming.hibernate.command.sql.GetUniqueResultImpl;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetUniqueResultTest {

    @Test(dataProvider = "get_student_impl")
    public void testGetStudent(GetUniqueResult getUniqueResult) throws Exception {
        Student student = getUniqueResult.get(SessionManager.getSession(), DataProcessor.getIds().get(0));
        Assert.assertTrue(DataProcessor.validateStudent(student));
    }

    @DataProvider(name = "get_student_impl")
    public Object[][] getStudentDataProvider() throws Exception {
        return new Object[][] {
                {new GetUniqueResultImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetUniqueResultImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetUniqueResultImpl()},
        };
    }
}
