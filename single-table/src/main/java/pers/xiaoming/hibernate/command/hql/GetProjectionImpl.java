package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByFuzzyName;
import pers.xiaoming.hibernate.command.get_interface.GetProjection;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetProjectionImpl implements GetProjection {

    // unlike sql, hql specify letter cases
    private final static String QUERY = "SELECT new Student(age, score) FROM Student WHERE age > :age";

    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, int age) throws Exception {
        try {
            session.beginTransaction();

            // no need for addEntity()
            List<Student> list = session.createQuery(QUERY)
                    .setParameter("age", age)
                    // just for save, not crash the memory
                    .setMaxResults(50).list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
