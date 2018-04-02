package pers.xiaoming.hibernate.command.one_to_many;

import org.hibernate.Session;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.GetEntity;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

public class GetPerson implements GetEntity<Person> {

    @Override
    public Person get(int id) {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Person person = session.get(Person.class, id);

            session.getTransaction().commit();

            return person;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
