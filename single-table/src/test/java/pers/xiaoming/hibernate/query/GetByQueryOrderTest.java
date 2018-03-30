package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByQueryOrderTest {
    @Test(dataProvider = "get_by_order_impl")
    public void testGetByOrderDesc(GetByOrder getByOrder) throws Exception {
        String orderByField = "score";

        // Omit the specify order by field for sql and hql
        //  if (getByOrder instanceof pers.xiaoming.hibernate.command.sql.GetByOrderImpl) {
        //      orderByField = "t_socre";
        //  }

        int maxResult = 6;

        // by default, return desc result
        List<Student> students = getByOrder.get(SessionManager.getSession(), orderByField, GetByOrder.QueryOrder.DESC, maxResult);

        Assert.assertEquals(students.size(), maxResult);
        System.out.println(students);
        Assert.assertTrue(students.get(0).getScore() >= students.get(1).getScore());
        Assert.assertTrue(students.get(1).getScore() >= students.get(2).getScore());
        Assert.assertTrue(students.get(2).getScore() >= students.get(3).getScore());
    }

    @Test(dataProvider = "get_by_order_impl")
    public void testGetByOrderAsc(GetByOrder getByOrder) throws Exception {
        String orderByField = "score";

        int maxResult = 6;

        // by default, return desc result
        List<Student> students = getByOrder.get(SessionManager.getSession(), orderByField, GetByOrder.QueryOrder.ASC, maxResult);

        Assert.assertEquals(students.size(), maxResult);
        System.out.println(students);
        Assert.assertTrue(students.get(0).getScore() <= students.get(1).getScore());
        Assert.assertTrue(students.get(1).getScore() <= students.get(2).getScore());
        Assert.assertTrue(students.get(2).getScore() <= students.get(3).getScore());

    }

    @DataProvider(name = "get_by_order_impl")
    public Object[][] getByOrderProvider() {
        return new Object[][] {
                {new pers.xiaoming.hibernate.command.sql.GetByOrderImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetByOrderImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetByOrderImpl()},
        };
    }


    @Test(dataProvider = "get_age_by_order_impl")
    public void testGetAgeByOrderDesc(GetByOrder getByOrder) throws Exception {
        String orderByField = "age";

        int maxResult = 6;

        // by default, return desc result
        List<Student> students = getByOrder.get(SessionManager.getSession(), orderByField, GetByOrder.QueryOrder.DESC, maxResult);

        Assert.assertEquals(students.size(), maxResult);
        System.out.println(students);
        Assert.assertTrue(students.get(0).getAge() >= students.get(1).getAge());
        Assert.assertTrue(students.get(1).getAge() >= students.get(2).getAge());
        Assert.assertTrue(students.get(2).getAge() >= students.get(3).getAge());
    }

    @DataProvider(name = "get_age_by_order_impl")
    public Object[][] getAgeByOrderProvider() {
        return new Object[][] {
                // {new pers.xiaoming.hibernate.command.sql.GetByOrderImpl()},
                // {new pers.xiaoming.hibernate.command.hql.GetByOrderImpl()},
                {new pers.xiaoming.hibernate.command.qbc.GetByOrderImpl()},
        };
    }

}
