package pers.xiaoming.hibernate.command.one_to_many;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.one_to_many.City;
import pers.xiaoming.hibernate.entity.one_to_many.Person;

public class GetFromTable {
    public City getCity(Session session, int id) {
        try {
            session.beginTransaction();

            City city = session.get(City.class, id);

            session.getTransaction().commit();

            return city;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public Person getPerson(Session session, int id) {
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
