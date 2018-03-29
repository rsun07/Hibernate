package pers.xiaoming.hibernate.command.left_join;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.City;
import pers.xiaoming.hibernate.entity.Person;

import java.util.List;

public class Hql implements GetCity {
    private static final String QUERY = "from City c left outer join c.residents where c.id = :city_id";

    @Override
    @SuppressWarnings("unchecked")
    public City get(Session session, int id) throws Exception {
        try {
            session.beginTransaction();

            int dbIndex = id + 1;

            // The query result is a List, but the generic type is an Object[]
            // The first element of the Object[] is City data, already encapsulated to City Class Object
            // The second element of the Object[] is Person data, already encapsulated to Person Class Object
            List<Object[]> queryResult = session.createQuery(QUERY).setInteger("city_id", dbIndex).list();

            City city = null;

            for (Object[] results : queryResult) {
                if (city == null) {
                    city = (City) results[0];
                }
                city.getResidents().add((Person) results[1]);
            }

            session.getTransaction().commit();

            return city;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
