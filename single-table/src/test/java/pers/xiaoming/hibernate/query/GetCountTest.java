package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetCount;
import pers.xiaoming.hibernate.session_factory.SessionFactory;

public class GetCountTest {
    @Test(dataProvider = "get_count_impl")
    public void testGetByPage(GetCount getCount) throws Exception {

        int count = getCount.get(SessionFactory.getSession()).intValue();
        Assert.assertEquals(DataProcessor.getNUM_OF_DATA_GENERATE(), count);
    }

    @DataProvider(name = "get_count_impl")
    public Object[][] getByPageDataProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetCountImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetCountImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetCountImpl()},
        };
    }
}
