package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetByPage;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByPageTest {
    @Test(dataProvider = "get_by_page_impl")
    public void testGetByPage(GetByPage getByPage) throws Exception {
        int pageSize = 2;
        int startIndex = 6;

        List<Student> students = getByPage.get(startIndex, pageSize);
        Assert.assertEquals(pageSize, students.size());
    }

    @DataProvider(name = "get_by_page_impl")
    public Object[][] getByPageDataProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetByPageImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetByPageImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetByPageImpl()},
        };
    }
}
