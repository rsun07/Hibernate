package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.command.sql.GetByOrderImpl;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByOrderTest {
    @Test(dataProvider = "get_by_order_impl")
    public void testGetByOrder(GetByOrder getByOrder) throws Exception {
        List<Student> students = getByOrder.get(SessionManager.getSession());
        Assert.assertEquals(DataProcessor.getNUM_OF_DATA_GENERATE(), students.size());
    }

    @DataProvider(name = "get_by_order_impl")
    public Object[][] getByOrderProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetByOrderImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetByOrderImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetByOrderImpl()},
        };
    }
}
