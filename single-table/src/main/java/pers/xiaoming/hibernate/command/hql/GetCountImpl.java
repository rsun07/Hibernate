package pers.xiaoming.hibernate.command.hql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetCount;
import pers.xiaoming.hibernate.entity.Student;

public class GetCountImpl implements GetCount {

    private final static String QUERY = "SELECT COUNT(id) FROM Student";

    @Override
    public Long get(Session session) {
        try {
            session.beginTransaction();

            Long count = (Long) session.createQuery(QUERY).uniqueResult();

            session.getTransaction().commit();

            return count;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
