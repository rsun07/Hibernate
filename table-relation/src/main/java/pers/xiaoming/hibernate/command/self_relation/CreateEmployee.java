package pers.xiaoming.hibernate.command.self_relation;

import org.hibernate.Session;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class CreateEmployee {
    public void create(Employee employee) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            // self relation is just a special case of one-to-may
            // in this case, the 'one' and 'many' share the same Class/Table
            session.save(employee);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
