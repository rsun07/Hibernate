package pers.xiaoming.hibernate.command.n_to_m;

import org.hibernate.Session;
import pers.xiaoming.hibernate.SessionManager;
import pers.xiaoming.hibernate.entity.n_to_m.Customer;

public class CreateCustomer {
    public void create(Customer customer) throws Exception {
        Session session = SessionManager.getSession();

        try {
            session.beginTransaction();

            session.save(customer);

            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        }
    }
}
