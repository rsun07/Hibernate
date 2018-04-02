package pers.xiaoming.hibernate.command.sql;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import pers.xiaoming.hibernate.command.get_interface.GetProjection;
import pers.xiaoming.hibernate.entity.Student;
import pers.xiaoming.hibernate.session_factory.SessionManager;

import java.util.List;

public class GetProjectionImpl implements GetProjection {


    // In order to mapping Table projection attributes to Class fields,
    // must set alias for the Table attributes
    // makes sure the alias are the same as the Class field names
    private final static String QUERY = "SELECT t_age age, t_score score FROM t_student WHERE t_age > ? LIMIT 50;";

    @SuppressWarnings("unchecked")
    public List<Student> get(int age) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            SQLQuery query = session.createSQLQuery(QUERY);

            // addEntity() NOT work in projection!
            // Error: "Column 't_id' not found."
            // query.addEntity(Student.class);


            // Should use Transformer here!!
            // aliasToBean() will create an empty Student Instance (So it require default Constructor)
            // then it assign value to the projection fields
            query.setResultTransformer(Transformers.aliasToBean(Student.class));


            query.setInteger(0, age);

            List<Student> list = query.list();

            session.getTransaction().commit();

            return list;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
