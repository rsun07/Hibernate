package pers.xiaoming.hibernate.command.create;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.City;

public class CreateCity {
    public void create(Session session, City city) throws Exception {

        try {
            session.beginTransaction();

            session.save(city);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
