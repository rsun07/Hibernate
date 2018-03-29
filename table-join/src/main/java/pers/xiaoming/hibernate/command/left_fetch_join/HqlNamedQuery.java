package pers.xiaoming.hibernate.command.left_fetch_join;

import org.hibernate.Query;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.GetCity;
import pers.xiaoming.hibernate.entity.City;

import java.util.List;

public class HqlNamedQuery implements GetCity {

    // query name defines in the hbm.xml
    private static final String QUERY_NAME = "selectCity";

    @Override
    @SuppressWarnings("unchecked")
    public City get(Session session, int id) throws Exception {
        try {
            session.beginTransaction();

            int dbIndex = id + 1;

            // query defines in hbm.xml
            Query query = session.getNamedQuery(QUERY_NAME);
            query.setInteger("city_id", dbIndex);


            City city = (City) query.uniqueResult();

            session.getTransaction().commit();

            return city;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
