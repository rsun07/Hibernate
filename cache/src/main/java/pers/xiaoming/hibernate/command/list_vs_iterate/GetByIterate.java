package pers.xiaoming.hibernate.command.list_vs_iterate;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.Iterator;
import java.util.List;

public class GetByIterate{

    private static final String QUERY = "FROM Student";

    public Iterator<Student> getAll(Session session) {
        try {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Iterator<Student> studentsItr = session.createQuery(QUERY).iterate();

            session.getTransaction().commit();

            return studentsItr;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
