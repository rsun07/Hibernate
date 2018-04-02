package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import pers.xiaoming.hibernate.command.get_interface.GetCount;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetCountImpl implements GetCount {

    @Override
    public Long get() {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class);
            criteria.setProjection(Projections.rowCount());

            // same as
            // criteria.setProjection(Projections.count("id"));

            Long count = (Long) criteria.uniqueResult();

            session.getTransaction().commit();

            return count;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
