package pers.xiaoming.hibernate.command.sql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByOrderImpl implements GetByOrder {
    private final static String QUERY = "SELECT t_id, t_name, t_age, t_score FROM t_student ORDER BY t_score DESC LIMIT 10;";

    @SuppressWarnings("unchecked")
    public List<Student> get(Session session) throws Exception {
        try {
            session.beginTransaction();

            List<Student> list = session.createSQLQuery(QUERY)
                    .addEntity(Student.class).list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
