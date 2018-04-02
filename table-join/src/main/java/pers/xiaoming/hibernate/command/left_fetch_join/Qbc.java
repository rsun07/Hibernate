package pers.xiaoming.hibernate.command.left_fetch_join;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.command.GetCity;
import pers.xiaoming.hibernate.entity.City;

public class Qbc implements GetCity {
    @Override
    @SuppressWarnings("unchecked")
    public City get(int id) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            int dbIndex = id + 1;

            Criteria criteria = session.createCriteria(City.class);
            criteria.createCriteria("residents", JoinType.LEFT_OUTER_JOIN);
            // same as the 'distinct' in the hql
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.eq("id", dbIndex));

            City city = (City) criteria.uniqueResult();

            session.getTransaction().commit();

            return city;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
