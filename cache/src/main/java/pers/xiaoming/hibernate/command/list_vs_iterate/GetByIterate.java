package pers.xiaoming.hibernate.command.list_vs_iterate;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.Iterator;
import java.util.List;

public class GetByIterate{

    private static final String QUERY = "FROM Student";

    public void getAll() {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            @SuppressWarnings("unchecked")
            Iterator<Student> students1 = session.createQuery(QUERY).iterate();
            while (students1.hasNext()) {
                System.out.println(students1.next());
            }

            System.out.println("\n------Separator for two queries--------\n");
            @SuppressWarnings("unchecked")
            Iterator<Student> students2 = session.createQuery(QUERY).iterate();
            while (students2.hasNext()) {
                System.out.println(students2.next());
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
