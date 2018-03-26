package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByOrderTest {
    @Test(dataProvider = "get_by_order_impl")
    public void testGetByOrder(GetByOrder getByOrder) throws Exception {
        String orderByField = "score";

        if (getByOrder instanceof pers.xiaoming.hibernate.command.sql.GetByOrderImpl) {
            orderByField = "t_socre";
        }

        int maxResult = 8;

        List<Student> students = getByOrder.get(SessionManager.getSession(), orderByField, maxResult);
        Assert.assertEquals(maxResult, students.size());
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
