package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByPage;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByPageImpl implements GetByPage {

    private static final String QUERY = "SELECT t_id, t_name, t_age, t_score FROM t_student " +
            "ORDER BY t_id LIMIT ? OFFSET ?";

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> get(int offset, int pageSize) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);
            query.setInteger(0, pageSize);
            query.setInteger(1, offset);
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
