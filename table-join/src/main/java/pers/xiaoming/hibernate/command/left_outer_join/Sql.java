package pers.xiaoming.hibernate.command.left_outer_join;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.City;
import pers.xiaoming.hibernate.entity.Person;

import java.util.List;

public class Sql implements GetCity {
    private static final String QUERY = "SELECT c.id, c.name, p.id, p.name FROM city AS c " +
            "LEFT JOIN person AS p ON c.id = p.city_id p WHERE c.id = :city_id";

    @Override
    @SuppressWarnings("unchecked")
    public City get(Session session, int id) throws Exception {
        try {
            session.beginTransaction();

            int dbIndex = id + 1;

            List<Object[]> queryResult = session.createSQLQuery(QUERY).setInteger("city_id", dbIndex).list();

            City city = null;

            for (Object[] results : queryResult) {
                City cityResult = new City();
                cityResult.setId((Integer) results[0]);
                cityResult.setName((String) results[1]);

                if (city == null) {
                    city = cityResult;
                }

                Person person = new Person();
                person.setId((Integer) results[2]);
                person.setName((String) results[3]);

                city.getResidents().add(person);
            }

            session.getTransaction().commit();

            return city;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
