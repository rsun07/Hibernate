package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByPage;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByPageImpl implements GetByPage {

    private static final String QUERY = "FROM Student";

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, int offset, int pageSize) throws Exception {
        try {
            session.beginTransaction();

            List<Student> students = session.createQuery(QUERY)
                    .setFirstResult(offset)
                    .setMaxResults(pageSize)
                    .list();

            session.getTransaction().commit();

            return students;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
