package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import pers.xiaoming.hibernate.command.get_interface.GetTopTenStudents;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetTopTenStudentsImpl implements GetTopTenStudents {
    @SuppressWarnings("unchecked")
    public List<Student> get(Session session) {
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class);
            criteria.addOrder(Order.desc("score"));
            criteria.setMaxResults(10);

            List<Student> list = criteria.list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}