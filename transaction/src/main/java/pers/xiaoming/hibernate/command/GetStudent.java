package pers.xiaoming.hibernate.command;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetStudent {
    public Student get(Session session, int id, Class studentClass) {
        try {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Student student = (Student) session.get(studentClass, id);

            session.getTransaction().commit();

            return student;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public List<Student> getAll(Session session, Class studentClass) {
        try {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Student> students = session.createQuery("FROM " + studentClass.getName()).list();

            session.getTransaction().commit();

            return students;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
