package pers.xiaoming.hibernate.command.left_fetch_join;

import org.hibernate.Query;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.GetCity;
import pers.xiaoming.hibernate.entity.City;
import pers.xiaoming.hibernate.entity.Person;

import java.util.List;

public class Hql implements GetCity {

    // In grammar, just replace 'join' to 'fetch join'
    // add a distinct to avoid duplication
    private static final String QUERY = "select distinct c from City c left outer join fetch c.residents where c.id = :city_id";

    @Override
    @SuppressWarnings("unchecked")
    public City get(Session session, int id) throws Exception {
        try {
            session.beginTransaction();

            int dbIndex = id + 1;

            // Fetch join already encapsulate the City and also the List<Person> residents in the City Object
            Query query = session.createQuery(QUERY);
            query.setInteger("city_id", dbIndex);

            // If get the list here rather than call the uniqueResult() method
            // There will be multiple duplicate City instances
            // For example, if the city has 5 residents Person, there will be five City instances
            // To avoid duplication, just add a 'distinct' key word in the query
            List<Object> list = query.list();

            City city = (City) query.uniqueResult();

            session.getTransaction().commit();

            return city;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
