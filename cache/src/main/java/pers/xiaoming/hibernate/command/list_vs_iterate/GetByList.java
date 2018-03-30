package pers.xiaoming.hibernate.command.list_vs_iterate;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByList {

    private static final String QUERY = "FROM Student";

    public void getAll(Session session) {
        try {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            List<Student> students1 = session.createQuery(QUERY).list();
            for (Student student : students1) {
                System.out.println(student);
            }
            System.out.println("\n------Separator for two queries--------\n");

            @SuppressWarnings("unchecked")
            List<Student> students2 = session.createQuery(QUERY).list();
            for (Student student : students2) {
                System.out.println(student);
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
