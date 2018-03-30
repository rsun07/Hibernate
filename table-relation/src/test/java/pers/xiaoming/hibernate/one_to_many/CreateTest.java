package pers.xiaoming.hibernate.one_to_many;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.GetEntity;
import pers.xiaoming.hibernate.command.one_to_many.CreateCity;
import pers.xiaoming.hibernate.command.one_to_many.GetPerson;
import pers.xiaoming.hibernate.entity.one_to_many.City;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

import java.util.HashSet;
import java.util.Set;

public class CreateTest {
    private Set<Person> residents;
    private City city;

    @BeforeTest
    public void setup() {
        residents = new HashSet<>();

        residents.add(new Person("Person1"));
        residents.add(new Person("Person2"));
        residents.add(new Person("Person3"));

        city = new City("MyCity", residents);
    }

    @Test
    public void testCreate() throws Exception {
        CreateCity createCity = new CreateCity();
        createCity.create(SessionManager.getSession(), city);
        verifyCreate();
    }

    private void verifyCreate() {
        GetEntity<Person> getPerson = new GetPerson();
        for (Person person : residents) {
            Assert.assertEquals(person,
                    getPerson.get(
                            SessionManager.getSession(),
                            person.getId()));
        }
    }
}
