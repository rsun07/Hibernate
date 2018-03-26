package pers.xiaoming.hibernate.command.one_to_many;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.one_to_many.City;

public class CreateCity {
    public void create(Session session, City city) throws Exception {

        try {
            session.beginTransaction();

            // if no cascade defines in "hibernate-mapping",
            // need to save person first to flush persons
            //  for (Person person: city.getResidents()) {
            //      session.save(person);
            //  }

            // 1. City is maintaining the relationship
            //  So we should save city here
            // 2. Whoever maintain the relationship,
            //  then we should save it
            // 3. Normally, it's the one-to-many,
            //  'one' rather than 'many' to maintain the relationship
            session.save(city);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
