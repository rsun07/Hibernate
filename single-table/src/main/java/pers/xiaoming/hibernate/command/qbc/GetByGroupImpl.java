package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import pers.xiaoming.hibernate.command.get_interface.GetByGroup;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

// Criteria doesn't support HAVING operation
public class GetByGroupImpl implements GetByGroup {

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> get(Session session, int appearance) {
        try {
            session.beginTransaction();

            // Criteria doesn't support HAVING operation
            Criteria criteria = session.createCriteria(Student.class);
            criteria.setProjection(Projections.groupProperty("age"));

            List<Object> ages = criteria.list();


            session.getTransaction().commit();

            return ages;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
