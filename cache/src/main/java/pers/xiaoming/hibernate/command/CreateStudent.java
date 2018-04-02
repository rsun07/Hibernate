package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class CreateStudent {
    public int create(Student student) {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            int id = (Integer) session.save(student);

            session.getTransaction().commit();

            return id;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
