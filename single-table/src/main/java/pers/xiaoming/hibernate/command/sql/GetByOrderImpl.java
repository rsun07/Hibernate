package pers.xiaoming.hibernate.command.sql;

import org.hibernate.Query;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByOrderImpl implements GetByOrder {

    // It's dangerous to set String into wildcard
    // So here by default, only support order by score
    // If want to support more fields, need to manually specify different queries
    // Dangerous!!!:
    // private final static String QUERY = "SELECT t_id, t_name, t_age, t_score FROM t_student ORDER BY ? ? LIMIT ?;";

    private final static String QUERY_DESC = "SELECT t_id, t_name, t_age, t_score FROM t_student ORDER BY t_score DESC LIMIT ?;";

    private final static String QUERY_ASC = "SELECT t_id, t_name, t_age, t_score FROM t_student ORDER BY t_score ASC LIMIT ?;";

    @SuppressWarnings("unchecked")
    public List<Student> get(String orderByField, QueryOrder queryOrder, int maxResult) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            String query = queryOrder.equals(QueryOrder.DESC) ? QUERY_DESC : QUERY_ASC;

            Query sqlQuery = session.createSQLQuery(query)
                    .addEntity(Student.class);
            sqlQuery.setInteger(0, maxResult);

            List<Student> students = sqlQuery.list();

            session.getTransaction().commit();
            return students;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
