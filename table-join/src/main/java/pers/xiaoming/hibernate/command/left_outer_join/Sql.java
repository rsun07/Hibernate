package pers.xiaoming.hibernate.command.left_outer_join;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.City;
import pers.xiaoming.hibernate.entity.Person;

import java.util.List;

public class Sql implements GetCity {
    private static final String QUERY = "SELECT c.id AS cid, c.name AS cname, p.id AS pid, p.name AS pname " +
            "FROM city AS c LEFT JOIN person AS p ON c.id = p.city_id WHERE c.id = :cityId";

    @Override
    @SuppressWarnings("unchecked")
    public City get(Session session, int id) throws Exception {
        try {
            session.beginTransaction();

            int dbIndex = id + 1;

            List<Object[]> queryResult = session.createSQLQuery(QUERY).setInteger("cityId", dbIndex).list();

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
