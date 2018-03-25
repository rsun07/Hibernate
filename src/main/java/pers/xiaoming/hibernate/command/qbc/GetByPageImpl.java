package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByPage;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByPageImpl implements GetByPage {

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, int offset, int pageSize) {
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class);
            criteria.setFirstResult(offset).setMaxResults(pageSize);

            List<Student> list = criteria.list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
