package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import pers.xiaoming.hibernate.command.get_interface.GetByOrder;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByOrderImpl implements GetByOrder {
    @SuppressWarnings("unchecked")
    public List<Student> get(String orderByField, QueryOrder queryOrder, int maxResult) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class);

            Order criteriaOrder;

            if (queryOrder.equals(QueryOrder.DESC)) {
                criteriaOrder = Order.desc(orderByField);
            } else {
                criteriaOrder = Order.asc(orderByField);
            }

            criteria.addOrder(criteriaOrder);
            criteria.setMaxResults(maxResult);

            List<Student> list = criteria.list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
