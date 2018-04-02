package pers.xiaoming.hibernate.command.one_to_many_mutual;

import org.hibernate.Session;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.entity.one_to_many_mutual.PersonM;

public class CreatePerson {
    public void create(PersonM person) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            session.save(person);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
