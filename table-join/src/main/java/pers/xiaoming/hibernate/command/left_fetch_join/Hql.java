package pers.xiaoming.hibernate.command.left_fetch_join;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.GetCity;
import pers.xiaoming.hibernate.entity.City;
import pers.xiaoming.hibernate.entity.Person;

import java.util.List;

public class Hql implements GetCity {

    // In grammar, just replace 'join' to 'fetch join'
    private static final String QUERY = "from City c left outer join fetch c.residents where c.id = :city_id";

    @Override
    @SuppressWarnings("unchecked")
    public City get(Session session, int id) throws Exception {
        try {
            session.beginTransaction();

            int dbIndex = id + 1;

            // Fetch join already encapsulate the City and also the List<Person> residents in the City Object
            City city = (City) session.createQuery(QUERY).setInteger("city_id", dbIndex).uniqueResult();

            session.getTransaction().commit();

            return city;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
