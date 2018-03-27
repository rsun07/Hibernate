package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetByGroup;
import pers.xiaoming.hibernate.session_factory.SessionFactory;

import java.util.List;

public class GetByGroupTest {
    @Test(dataProvider = "get_by_group_impl")
    public void testGetByGroup(GetByGroup getByGroup) throws Exception {
        // only age 20 will appear more than 3 times
        int appearance = 3;

        List<Object> ages = getByGroup.get(SessionFactory.getSession(), appearance);

        Assert.assertEquals(ages.size(), 1);
        for (Object age : ages) {
            Assert.assertEquals(age, 20);
        }
    }

    @DataProvider(name = "get_by_group_impl")
    public Object[][] getByPageDataProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetByGroupImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetByGroupImpl()},
//                {new pers.xiaoming.hibernate.command.qbc.GetByGroupImpl()},
        };
    }
}
