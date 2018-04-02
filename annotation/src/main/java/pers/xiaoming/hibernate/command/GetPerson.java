package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Person;

public class GetPerson {

    public Person get(Session session, int id) {
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
