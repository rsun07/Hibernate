package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetTopTenStudents;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetTopTenStudentsImpl implements GetTopTenStudents {

    // limit is not supported in HQL,
    // need to use setMaxResults(maxRows) function
    private final static String HQL_QUERY = "FROM Student ORDER BY score DESC";
    @SuppressWarnings("unchecked")
    public List<Student> get(Session session) {
        try {
            session.beginTransaction();

            // no need for addEntity()
            List<Student> list = session.createQuery(HQL_QUERY).setMaxResults(10).list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}