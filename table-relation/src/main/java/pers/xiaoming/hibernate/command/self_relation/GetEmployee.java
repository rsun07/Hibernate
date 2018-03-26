package pers.xiaoming.hibernate.command.self_relation;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.GetEntity;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class GetEmployee implements GetEntity<Employee> {

    @Override
    public Employee get(Session session, int id) {
        try {
            session.beginTransaction();

            Employee employee = session.get(Employee.class, id);

            session.getTransaction().commit();

            return employee;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
