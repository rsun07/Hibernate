package pers.xiaoming.hibernate.command.n_to_m;

import org.hibernate.Session;
import pers.xiaoming.hibernate.entity.n_to_m.Customer;

public class CreateStoreAndCustomer {
    public void create(Session session, Customer customer) throws Exception {

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
