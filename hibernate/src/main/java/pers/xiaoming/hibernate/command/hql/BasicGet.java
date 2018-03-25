package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.GetStudent;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class BasicGet implements GetStudent {
    // Student here is class name rather than table name
    private final static String HQL_WILDCARD_QUERY = "FROM Student WHERE id = ?;";

    private final static String HQL_PARAM_QUERY = "FROM Student WHERE id = :id;";



    @SuppressWarnings("unchecked")
    public Student get(Session session, int id) {
        try {
            session.beginTransaction();

//            List<Student> list = session.createQuery(HQL_PARAM_QUERY)
//                    .setInteger(0, id).list();
            List<Student> list = session.createQuery(HQL_WILDCARD_QUERY)
                    .setInteger("id", id).list();

            session.getTransaction().commit();
            return list.get(0);

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
