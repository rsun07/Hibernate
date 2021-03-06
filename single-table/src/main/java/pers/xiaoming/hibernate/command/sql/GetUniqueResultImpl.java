package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetUniqueResult;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetUniqueResultImpl implements GetUniqueResult {
    // In SQL, use database table attribute names instead of Java Class field names
    private final static String QUERY = "SELECT t_id, t_name, t_age, t_score FROM t_student WHERE t_id = ? LIMIT 10;";

    @SuppressWarnings("unchecked")
    public Student get(int id) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);
            query.setInteger(0, id);
            query.addEntity(Student.class);
            List<Student> list = query.list();

            session.getTransaction().commit();

            return list.get(0);

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
