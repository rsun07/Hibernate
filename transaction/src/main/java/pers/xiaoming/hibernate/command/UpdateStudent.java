package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

public class UpdateStudent {
    public boolean update(Session session, Student student) {
        try {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
