package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetStudent;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetStudentImpl implements GetStudent {
    // Student here is class name rather than table name
    private final static String HQL_WILDCARD_QUERY = "FROM Student WHERE id = ?";

    private final static String HQL_PARAM_QUERY = "FROM Student WHERE id = :id";

    private final static String HQL_SELECT_QUERY =
            "SELECT new Student(id, name, age, score) FROM Student WHERE id = :id";

    @SuppressWarnings("unchecked")
    public Student get(Session session, int id) {
        try {
            session.beginTransaction();

//            List<Student> list = session.createQuery(HQL_WILDCARD_QUERY)
//                    .setInteger(0, id).list();
            Student student = (Student) session.createQuery(HQL_PARAM_QUERY)
                    .setInteger("id", id).uniqueResult();

            session.getTransaction().commit();
            return student;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
