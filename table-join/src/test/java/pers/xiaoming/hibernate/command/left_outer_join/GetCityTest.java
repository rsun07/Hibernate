package pers.xiaoming.hibernate.command.left_outer_join;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.SessionFactory;
import pers.xiaoming.hibernate.entity.City;

public class GetCityTest {
    @Test(dataProvider = "left_outer_join")
    public void testGetCity(GetCity getCity) throws Exception {
        int cityId = 2;
        City returnCity = getCity.get(SessionFactory.getSession(), cityId);
        Assert.assertEquals(returnCity, InitDb.getCities().get(cityId));
    }

    @DataProvider(name = "left_outer_join")
    public Object[][] daoProvider() {
        return new Object[][] {
                {new Hql()},
                {new Sql()},
                {new Qbc()},
        };
    }
}
