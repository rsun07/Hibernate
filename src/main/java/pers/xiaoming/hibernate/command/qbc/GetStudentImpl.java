package pers.xiaoming.hibernate.command.qbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pers.xiaoming.hibernate.command.get_interface.GetStudent;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetStudentImpl implements GetStudent {

    @SuppressWarnings("unchecked")
    public Student get(Session session, int id) {
        try {
            session.beginTransaction();

            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.like("id", id));

            List<Student> list = criteria.list();

            session.getTransaction().commit();
            return list.get(0);

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
