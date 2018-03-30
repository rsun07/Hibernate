package pers.xiaoming.hibernate.command.list_vs_iterate;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByList {

    private static final String QUERY = "FROM Student";

    public List<Student> getAll(Session session) {
        try {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Student> students = session.createQuery(QUERY).list();

            session.getTransaction().commit();

            return students;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
