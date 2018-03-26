package pers.xiaoming.hibernate.command.one_to_many;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.one_to_many.City;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

public class CreateCity {
    public void create(Session session, City city) throws Exception {

        try {
            session.beginTransaction();

            // if no cascade defines in "hibernate-mapping",
            // need to save person first to flush persons
            //  for (Person person: city.getResidents()) {
            //      session.save(person);
            //  }

            session.save(city);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
