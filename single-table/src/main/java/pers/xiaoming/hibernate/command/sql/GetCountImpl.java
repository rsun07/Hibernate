package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetCount;
import pers.xiaoming.hibernate.entity.Student;

import java.math.BigInteger;



public class GetCountImpl implements GetCount {

    // COUNT(*) could be replaced by COUNT(t_id) as t_id is the primary key
    private final static String QUERY = "SELECT COUNT(*) FROM t_student;";

    @Override
    public Long get(Session session) {
        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);

            // Hibernate may have issue to mapping the count result to given entity Student.class
            // for example, "column not found for t_id"

            // So, Remove the Entity for count
            // query.addEntity(Student.class);

            // the return type is a BigInteger
            BigInteger count = (BigInteger) query.uniqueResult();

            session.getTransaction().commit();

            return Long.valueOf(count.toString());

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
