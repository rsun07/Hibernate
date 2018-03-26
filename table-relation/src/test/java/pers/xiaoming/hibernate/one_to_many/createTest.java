package pers.xiaoming.hibernate.one_to_many;

import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.one_to_many.CreateCity;
import pers.xiaoming.hibernate.entity.one_to_many.City;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

public class createTest {
    @Test
    public void testCreate() throws Exception {
        CreateCity createCity = new CreateCity();


        Person person1 = new Person("Person1");
        Person person2 = new Person("Person2");
        Person person3 = new Person("Person3");

        City city = new City("MyCity");
        city.getResidents().add(person1);
        city.getResidents().add(person2);
        city.getResidents().add(person3);

        createCity.create(SessionManager.getSession(), city);


    }
}
