package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

public class CreateStudent {
    public int create(Session session, Student student) {
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
