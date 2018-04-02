package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByFuzzyName;
import pers.xiaoming.hibernate.command.get_interface.GetProjection;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetProjectionImpl implements GetProjection {

    private final static String QUERY = "SELECT new Student(age, score) FROM Student WHERE age > :age";

    @SuppressWarnings("unchecked")
    public List<Student> get(int age) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            // no need for addEntity()
            List<Student> students = session.createQuery(QUERY)
                    .setParameter("age", age)
                    // just for save, not crash the memory
                    .setMaxResults(50).list();

            session.getTransaction().commit();

            return students;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
