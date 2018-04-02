package pers.xiaoming.hibernate.command.left_join;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.InitDb;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.GetCity;
import pers.xiaoming.hibernate.entity.City;

public class GetCityTest {
    @Test(dataProvider = "left_join")
    public void testGetCity(GetCity getCity) throws Exception {
        int cityId = 2;
        City returnCity = getCity.get(cityId);
        Assert.assertEquals(returnCity, InitDb.getCities().get(cityId));
    }

    @DataProvider(name = "left_join")
    public Object[][] daoProvider() {
        return new Object[][] {
                {new Hql()},
                {new Sql()},
                {new Qbc()},
        };
    }
}
