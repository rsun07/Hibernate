package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByFuzzyName;
import pers.xiaoming.hibernate.entity.Student;

import java.util.List;

public class GetByFuzzyNameImpl implements GetByFuzzyName {

    private static final String QUERY = "SELECT t_id, t_name, t_age, t_score FROM t_student " +
            "WHERE t_name like %?% LIMIT 10";

    @Override
    @SuppressWarnings("unchecked")
    public List<Student> get(Session session, String fuzzyName) throws Exception {

        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);
            query.setString(0, fuzzyName);
            query.addEntity(Student.class);
            List<Student> list = query.list();

            session.getTransaction().commit();

            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
