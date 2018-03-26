package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetCount;
import pers.xiaoming.hibernate.entity.Student;

public class GetCountImpl implements GetCount {

    private final static String QUERY = "SELECT count(*) from t_student;";

    @Override
    public Long get(Session session) {
        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);
            query.addEntity(Student.class);
            Long count = (Long) query.uniqueResult();

            session.getTransaction().commit();

            return count;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
