package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetByFuzzyName;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByFuzzyNameTest {
    @Test(dataProvider = "get_by_fuzzy_name_impl")
    public void testGetByFuzzyName(GetByFuzzyName getStudents) throws Exception {
        List<Student> students = getStudents.get(DataProcessor.getNAME_PREFIX());
        Assert.assertEquals(DataProcessor.getNUM_OF_DATA_GENERATE(), students.size());
    }

    @DataProvider(name = "get_by_fuzzy_name_impl")
    public Object[][] getTopTenStudentDataProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetByFuzzyNameImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetByFuzzyNameImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetByFuzzyNameImpl()},
        };
    }
}
