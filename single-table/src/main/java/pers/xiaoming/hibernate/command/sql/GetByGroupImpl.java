package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByGroup;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByGroupImpl implements GetByGroup {
    private static final String QUERY = "SELECT t_id, t_name, t_age, t_score FROM t_student " +
            "GROUP BY t_age HAVING count(t_age) > :appearance";

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, int appearance) {
        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);
            query.setInteger(0, appearance);
            query.addEntity(Student.class);

            List<Student> list = query.list();

            session.getTransaction().commit();

            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
