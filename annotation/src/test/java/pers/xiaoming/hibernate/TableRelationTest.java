package pers.xiaoming.hibernate;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.command.CreateCity;
import pers.xiaoming.hibernate.command.LeftFetchJoin;
import pers.xiaoming.hibernate.entity.City;
import pers.xiaoming.hibernate.entity.Person;

import java.util.HashSet;
import java.util.Set;

public class TableRelationTest {
    private City city;

    @BeforeTest
    public void setup() {
        Set<Person> residents = new HashSet<>();

        residents.add(new Person("Person1"));
        residents.add(new Person("Person2"));
        residents.add(new Person("Person3"));

        city = new City("MyCity", residents);
    }

    @Test
    public void testCreate() throws Exception {
        CreateCity createCity = new CreateCity();
        createCity.create(city);
        verifyCreate();
    }

    private void verifyCreate() {
        LeftFetchJoin cityGetter = new LeftFetchJoin();
        City city = cityGetter.get(1);
        Assert.assertEquals(city, this.city);
    }
}
