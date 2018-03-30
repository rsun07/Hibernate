package pers.xiaoming.hibernate.command.list_vs_iterate;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;

import java.util.Iterator;
import java.util.List;

public class GetByListThenIterate {
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
            Iterator<Student> students2 = session.createQuery(QUERY).iterate();
            while (students2.hasNext()) {
                System.out.println(students2.next());
            }

            System.out.println("\n------Separator for two queries--------\n");

            @SuppressWarnings("unchecked")
            Iterator<Student> students3 = session.createQuery(QUERY).iterate();
            while (students3.hasNext()) {
                System.out.println(students3.next());
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
