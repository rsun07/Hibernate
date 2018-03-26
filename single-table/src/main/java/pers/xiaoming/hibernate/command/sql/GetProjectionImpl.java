package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetProjection;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

// It's not easy to run projection through sql
public class GetProjectionImpl implements GetProjection {

    private final static String QUERY = "SELECT t_age, t_score FROM t_student WHERE t_age > ? LIMIT 50;";

    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, int age) throws Exception {
        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);
            query.addEntity(Student.class);
            query.setInteger(0, age);
            List<Student> list = query.list();

            session.getTransaction().commit();

            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
