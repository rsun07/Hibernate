package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByGroup;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByGroupImpl implements GetByGroup {
    private static final String QUERY = "SELECT t_age age FROM t_student " +
            "GROUP BY age HAVING count(age) > :appearance";

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> get(int appearance) {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);
            query.setParameter("appearance", appearance);

            // Same as Projection
            // No need for entity here
            // query.addEntity(Student.class);

            List<Object> list = query.list();

            session.getTransaction().commit();

            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
