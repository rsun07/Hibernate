package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByOrderImpl implements GetByOrder {

    // limit is not supported in HQL,
    // need to use setMaxResults(maxRows) function
    private final static String HQL_QUERY = "FROM Student ORDER BY :orderByField DESC";
    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, String orderByField, int maxResult) throws Exception {
        try {
            session.beginTransaction();

            // no need for addEntity()
            List<Student> list = session.createQuery(HQL_QUERY)
                    .setParameter("orderByField", orderByField)
                    .setMaxResults(maxResult).list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
