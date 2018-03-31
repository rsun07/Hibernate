package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

public class GetStudent {
    public Student get(Session session, int id) {
        try {
            session.beginTransaction();

            Student student = session.get(Student.class, id);

            session.getTransaction().commit();

            return student;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
