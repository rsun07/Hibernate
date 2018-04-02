package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByFuzzyName;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetByFuzzyNameImpl implements GetByFuzzyName {

    // unlike sql, hql specify letter cases
    private final static String QUERY = "FROM Student WHERE name like :fuzzyName";

    @SuppressWarnings("unchecked")
    public List<Student> get(String nameLike) throws Exception {
        Session session = SessionManager.getSession();
        try {
            session.beginTransaction();

            // no need for addEntity()
            List<Student> list = session.createQuery(QUERY)
                    .setParameter("fuzzyName", getFuzzyName(nameLike))
                    .setMaxResults(10).list();

            session.getTransaction().commit();
            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
