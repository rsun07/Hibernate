package pers.xiaoming.hibernate.command.self_relation;

import org.hibernate.Session;
import pers.xiaoming.hibernate.command.GetEntity;
import pers.xiaoming.hibernate.entity.self_relation.Employee;

public class GetEmployee {
    public Employee getEmployee(Session session, int id) {
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


    private static final String SQL_QUERY = "select * from employee where id = "
            + "(select manager_id from employee where id = ?)";

    private static final String HQL_QUERY = "select new Employee(name, title) from Employee where id = "
            + "(select manager_id from Employee where id = :id)";

    public Employee getManager(Session session, int id) {
        try {
            session.beginTransaction();

            Employee manager = (Employee) session.createQuery(HQL_QUERY).setInteger("id", id).uniqueResult();

            session.getTransaction().commit();

            return manager;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
