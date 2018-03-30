package pers.xiaoming.hibernate.one_to_many_mutual;

import org.testng.annotations.Test;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.one_to_many_mutual.CreatePerson;
import pers.xiaoming.hibernate.entity.one_to_many_mutual.CityM;
import pers.xiaoming.hibernate.entity.one_to_many_mutual.PersonM;

public class CreateTest {

    @Test
    public void testCreate() throws Exception {
        CreatePerson createPerson = new CreatePerson();

        CityM city = new CityM("MyCityM");

        PersonM person1 = new PersonM("John");
        person1.setCity(city);
        createPerson.create(SessionManager.getSession(), person1);

        PersonM person2 = new PersonM("Mike");
        person2.setCity(city);
        createPerson.create(SessionManager.getSession(), person2);
    }
}
