package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class GetStudent {
    public Student get(int id) {
        Session session = SessionManager.getSession();

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
