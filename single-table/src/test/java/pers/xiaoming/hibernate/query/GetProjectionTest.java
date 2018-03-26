package pers.xiaoming.hibernate.query;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.get_interface.GetProjection;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionFactory;

import java.util.List;

public class GetProjectionTest {
    @Test(dataProvider = "get_projection_impl")
    public void testGetByPage(GetProjection getProjection) throws Exception {

        int ageMin = 21;

        List<Student> students = getProjection.get(SessionFactory.getSession(), ageMin);

        for (Student student : students) {
            Assert.assertNotNull(student.getAge());
            Assert.assertNotNull(student.getScore());
            Assert.assertNull(student.getId());
            Assert.assertNull(student.getName());
            Assert.assertTrue(student.getAge() > ageMin);
        }
    }

    @DataProvider(name = "get_projection_impl")
    public Object[][] getByPageDataProvider() {
        return new Object[][] {
                // It's not easy to run projection using sql
                // ignore it
                // {new pers.xiaoming.hibernate.command.sql.GetProjectionImpl()},
                {new pers.xiaoming.hibernate.command.hql.GetProjectionImpl()},
//                {new pers.xiaoming.hibernate.command.qbc.GetProjectionImpl()},
        };
    }
}
