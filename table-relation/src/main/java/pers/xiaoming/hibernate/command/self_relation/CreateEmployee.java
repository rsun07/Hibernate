package pers.xiaoming.hibernate.command.self_relation;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class CreateEmployee {
    public void create(Session session, Employee employee) throws Exception {
        try {
            session.beginTransaction();

            session.save(employee);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
