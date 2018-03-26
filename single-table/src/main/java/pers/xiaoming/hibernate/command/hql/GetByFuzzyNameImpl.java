package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByFuzzyName;
import pers.xiaoming.hibernate.command.get_interface.GetTopTenStudents;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByFuzzyNameImpl implements GetByFuzzyName {

    // unlike sql, hql specify letter cases
    private final static String HQL_QUERY = "FROM Student WHERE name like :fuzzyName";

    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, String nameLike) throws Exception {
        try {
            session.beginTransaction();

            // no need for addEntity()
            List<Student> list = session.createQuery(HQL_QUERY)
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
