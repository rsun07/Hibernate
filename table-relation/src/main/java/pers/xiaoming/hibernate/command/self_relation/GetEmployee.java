package pers.xiaoming.hibernate.command.self_relation;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import pers.xiaoming.hibernate.entity.self_relation.DBEmployee;
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
            + "(select manager_id from employee where id = :id)";

    private static final String HQL_QUERY = "select new Employee(name, title) from Employee where id = "
            + "(select manager_id from Employee where id = :id)";

    public DBEmployee getManager(Session session, int id) {
        try {
            session.beginTransaction();

            Query query = session.createSQLQuery(SQL_QUERY).setInteger("id", id);
            query.setResultTransformer(Transformers.aliasToBean(DBEmployee.class));

            // java.lang.ClassCastException: pers.xiaoming.hibernate.entity.self_relation.DBEmployee cannot be cast to java.util.Map
            // if the sql query is "select *" because there is a "managerId" field which hibernate cannot mapping from table attribute to
            // either change the query to specify the attributes with alias, which exactly match the table attribute
            // or change the field name in the DBEmployee class to match the table attribute
            DBEmployee manager = (DBEmployee) query.uniqueResult();

            session.getTransaction().commit();

            return manager;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
