package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pers.xiaoming.hibernate.command.get_interface.GetByFuzzyName;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByFuzzyNameImpl implements GetByFuzzyName {
    @SuppressWarnings("unchecked")
    public List<Student> get(String nameLike) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class)
                    // Still need the name has '%' wildcard on it!
                    .add(Restrictions.like("name", getFuzzyName(nameLike)));

            List<Student> list = criteria.list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
