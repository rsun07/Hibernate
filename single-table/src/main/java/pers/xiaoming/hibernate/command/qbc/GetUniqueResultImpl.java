package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pers.xiaoming.hibernate.command.get_interface.GetUniqueResult;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetUniqueResultImpl implements GetUniqueResult {

    @SuppressWarnings("unchecked")
    public Student get(int id) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.in("id", id));

            Student student = (Student) criteria.uniqueResult();

            session.getTransaction().commit();
            return student;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
