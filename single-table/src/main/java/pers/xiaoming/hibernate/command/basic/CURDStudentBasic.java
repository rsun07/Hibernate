package pers.xiaoming.hibernate.command.basic;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

public class CURDStudentBasic {

    /*
     * save() running process:
     * 1. ping DB, generate an id
     * 2. receive the id, then using this id to initialize the instance
     * (assign the id to student object)
     * 3. now, student has an id even though the session hasn't been committed
     * that's to say, the student object is put into the Hibernate Session cache.
     *
     *
     */
    public int create(Student student) throws Exception {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();
            int id = (Integer) session.save(student);
            session.getTransaction().commit();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }
    }

    public void delete(int id) throws Exception {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();
            session.delete(Student.builder().id(id).build());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }
    }

    public void update(Student student) throws Exception {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }
    }

    public Student get(int id) throws Exception {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();
            Student studentFind = session.get(Student.class, id);
            session.getTransaction().commit();
            return studentFind;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            throw e;
        }
    }
}
