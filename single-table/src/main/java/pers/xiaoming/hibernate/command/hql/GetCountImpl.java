package pers.xiaoming.hibernate.command.hql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetCount;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetCountImpl implements GetCount {

    private final static String QUERY = "SELECT COUNT(id) FROM Student";

    @Override
    public Long get() {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();

            // The result, in fact, is a Java Long
            // In Java, Long cannot be cast to Integer
            // long could be cast to int
            // cast Long to Integer, use Long.intValue()
            Long count = (Long) session.createQuery(QUERY).uniqueResult();

            session.getTransaction().commit();

            return count;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
