package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByOrderImpl implements GetByOrder {

    // limit is not supported in HQL,
    // need to use setMaxResults(maxRows) function

    // It's dangerous to inject a String as a parameter
    // so define them ahead of time
    private final static String QUERY_DESC = "FROM Student ORDER BY score DESC";

    private final static String QUERY_ASC = "FROM Student ORDER BY score ASC";

    @SuppressWarnings("unchecked")
    public List<Student> get(String orderByField, QueryOrder queryOrder, int maxResult) throws Exception {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();

            String query = queryOrder.equals(QueryOrder.DESC) ? QUERY_DESC : QUERY_ASC;

            // no need for addEntity()
            List<Student> list = session.createQuery(query)
                    .setMaxResults(maxResult).list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
