package pers.xiaoming.hibernate.command.hql;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.get_interface.GetByGroup;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GetByGroupImpl implements GetByGroup {
    private static final String QUERY = "SELECT age FROM Student " +
            "GROUP BY age HAVING count(age) > :appearance";

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> get(Session session, int appearance) {
        try {
            session.beginTransaction();

            List<Object> ages = session.createQuery(QUERY)
                    // If you can use setInteger()
                    // Don't use setParameter()
                    // setParameter() will cause issue here
                    .setInteger("appearance", appearance)
                    .list();

            session.getTransaction().commit();

            return ages;

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
