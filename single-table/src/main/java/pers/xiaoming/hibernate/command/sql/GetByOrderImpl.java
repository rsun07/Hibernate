package pers.xiaoming.hibernate.command.sql;

import org.hibernate.Query;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByOrderImpl implements GetByOrder {
    private final static String QUERY = "SELECT t_id, t_name, t_age, t_score FROM t_student ORDER BY ? DESC LIMIT ?;";

    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, String orderByField, int maxResult) throws Exception {
        try {
            session.beginTransaction();

            Query query = session.createSQLQuery(QUERY)
                    .addEntity(Student.class);
            query.setString(0, orderByField);
            query.setInteger(1, maxResult);

            List<Student> students = query.list();

            session.getTransaction().commit();
            return students;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
