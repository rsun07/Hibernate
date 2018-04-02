package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByPage;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByPageImpl implements GetByPage {

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> get(int offset, int pageSize) throws Exception {
        Session session = SessionManager.getSession();

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
