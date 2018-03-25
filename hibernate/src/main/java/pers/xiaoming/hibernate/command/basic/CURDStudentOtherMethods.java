package pers.xiaoming.hibernate.command.basic;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

public class CURDStudentOtherMethods {
    public int persist(Session session, Student student) {
        try {
            session.beginTransaction();
            // same as
            // session.getTransaction().begin();

            // return type is void
            session.persist(student);

            session.getTransaction().commit();

            return student.getId();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    public void saveOrUpdate(Session session, Student student) {
        try {
            session.beginTransaction();

            // if primary key is specified (even though it not exist)
            // will do update
            // otherwise do create / save
            session.saveOrUpdate(student);

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }

    /*
     * Diff between load() and get()
     * 1. load() will throw exception if object not exist
     * 2. load() will return a proxy by default and db won't be hit until the proxy is first invoked
     */
    public Student load(Session session, int id) {
        try {
            session.beginTransaction();

            Student student = session.load(Student.class, id);

            session.getTransaction().commit();
            return student;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
