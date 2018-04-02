package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Person;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetPerson {

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
