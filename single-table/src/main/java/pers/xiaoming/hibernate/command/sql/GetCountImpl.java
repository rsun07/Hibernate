package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetCount;
import pers.xiaoming.hibernate.entity.Student;


// There is a JDBC bug for column name which causes Hibernate to fail in queries.
public class GetCountImpl implements GetCount {

    private final static String QUERY = "SELECT COUNT(t_name) FROM t_student;";

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
